package com.jingyou.jybase.web.security;

import com.jingyou.jybase.common.util.ObjectUtil;
import com.jingyou.jybase.common.util.PwdUtil;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.service.sys.SysParamService;
import com.jingyou.jybase.service.sys.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 系统安全认证实现类
 * @author fgxue
 * @version 2013-5-29
 */
@Service
public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {
	@Autowired
	private UserService userService;
	/***
	 * 获取认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserBean user = userService.findByAccount(token.getUsername());
		if (user == null) {
			throw new AccountException("用户不存在!");
		}

		if(user.getEnabled() == 0){
			throw new AccountException("用户已禁用!");
		}

		if(user.getExpireTime() != null && (new DateTime(user.getExpireTime()).isBeforeNow())){
			throw new AccountException("用户已过期!");
		}

		boolean  rst = PwdUtil.validatePassword(new String(token.getPassword()),user.getPwd());
		if(!rst){
			throw new AccountException("用户密码错误!");
		}

		/**
		 * 盐值加密
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getAccount());
			new SimpleAuthenticationInfo(user.getAccount(),user.getPwd(),credentialsSalt,getName());
		 */
		return new SimpleAuthenticationInfo(user.getAccount(),token.getPassword(),getName());
	}

	/***
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		String account = (String) principals.getPrimaryPrincipal();
		UserBean user = userService.findByAccount(account);
		List<ResourceBean>  ress = userService.getRessByUserId(user.getId());
		Set<String> pers = new HashSet<String>();
		for(ResourceBean res : ress){
			pers.add(res.getName());
		}
		auth.setStringPermissions(pers);
		return auth;
	}

	public static void main(String[] args) {
		//MD5盐值加密，不同用户相同密码加密后结果不一样
		Object result = new SimpleHash("admin","123456","admin",1024);
		Object result2 = new SimpleHash("test","123456","test",1024);
		System.out.printf(result.toString());
		System.out.printf(result2.toString());
	}
}
