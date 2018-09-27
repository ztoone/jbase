<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/filter/item/filterItem.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑参数</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="editForm" style="padding-top:10px;padding-left:15px;">
            <input type="hidden" name="id" value="${item.id}"/>
            <input type="hidden" name="filterId" value="${item.filterId}"/>
            <table id="filter_table" width="90%">
            <tr>
                <td>属性：</td>
                <td>
                    <input type="text" class="easyui-validatebox input-readonly" name="field" maxlength="100" readonly style="width:60%;" value="${item.field}"/>
                </td>
            </tr>

                <tr>
                    <td>属性名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="fieldName" maxlength="100" data-options="required:true" missingMessage="属性名称不能为空!" style="width:60%;" value="${item.fieldName}"/>
                    </td>
                </tr>

                <tr>
                    <td>比较方式：</td>
                    <td>
                        <select class="easyui-combobox" id="restriction" name="restriction" style="width:100px">
                            <option value="Like">包含</option>
                            <option value="Eq">相等</option>
                            <option value="Between">两者之间</option>
                            <option value="Gt">大于</option>
                            <option value="Lt">小于</option>
                            <option value="Ge">大于等于</option>
                            <option value="Le">小于等于</option>
                            <option value="LLike">左包含</option>
                            <option value="RLike">右包含</option>
                        </select>
                        <script>$("#restriction").val('${item.restriction}')</script>
                    </td>
                </tr>
                <tr>
                    <td>类型：</td>
                    <td>
                        <select class="easyui-combobox" id="fieldType" name="fieldType" style="width:100px">
                            <option value="text">文本</option>
                            <option value="select">下拉</option>
                            <option value="date">日期</option>
                            <option value="datetime">日期时间</option>
                        </select>
                        <script>$("#fieldType").val('${item.fieldType}')</script>
                    </td>
                </tr>
                <tr>
                    <td>表达式：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="value" maxlength="100" style="width:60%;" value="${item.value}" }/>
                    </td>
                </tr>

            <tr>
                <td>排序：</td>
                <td>
                    <input type="text" class="easyui-validatebox" name="order" maxlength="100" data-options="required:true" style="width: 100px;" missingMessage="排序不能为空!" value="${item.order}"/>
                </td>
            </tr>
                <tr>
                    <td>是否启用：</td>
                    <td>
                        <label>启用</label>
                        <input type="radio" name="enabled" value="1" checked="checked"/>
                        <label>不启用</label>
                        <input type="radio" name="enabled" value="0"/>
                        <script>setRadio("editForm","enabled",${item.enabled});</script>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south',border:false">
        <div class="dialog-button">
            <a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="update()">确定</a>
            <a id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="window.parent.closeDialog();">取消</a>
        </div>
    </div>
</div>
</body>
</html>