/**
 * Created by Administrator on 2016/6/6 0006.
 */
(function($) {

    /**
     * 新增 remote 校验规则
     * 功能：对控件做唯一性校验
     */
    $.extend($.fn.validatebox.defaults.rules, {
        remote: {
            validator: function(value,args) {
                var url = args[0];
                for(var i=1;i<args.length;i++){
                    url = url +"/" + args[i];
                }
                var flag = true;
                $.ajax({
                    url: url,
                    type: "POST",
                    async:false,
                    data:{
                        value:value
                    },
                    dataType: "json",
                    success: function (data) {
                        flag = data.status;
                    }
                });
                return !flag;
            },
            message:'已存在!'
        },
        minLength: {//最小长度
            validator: function(value, param){
                return value.length >= param[0];
            },
            message: '至少输入 {0}个字符'
        },
        maxLength: {//最大长度
            validator: function(value, param){
                return value.length <= param[0];
            },
            message: '最多输入 {0}个字符'
        },
        equals: {//两个内容相同
            validator: function(value,param){
                return value == $(param[0]).val();
            },
            message: '两次输入的内容不匹配'
        },
        chinese:{
            // 验证中文
            validator:function (value) {
                return /^[\Α-\￥]+$/i.test(value);
            },
            message: '请输入中文'
        }
    });
})(jQuery);