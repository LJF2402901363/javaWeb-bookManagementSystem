<%@ page import="domain.Account" %><%--
  Created by IntelliJ IDEA.
  User: 陌意随影
  Date: 2020/2/1 0001
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-dialog.min.css">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-dialog.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <title>图书后台管理主页面</title>
</head>
<body>
<div>


    <nav class="navbar navbar-default" role="navigation" style="background: #393D49;">
        <div class="container-fluid">
            <div class="navbar-header" style="margin-left: 40%;">
                <a class="navbar-brand" href="#" style="color: #01AAED;">图书后台管理主页面</a>
            </div>
            <div>
                <!--向右对齐-->
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            管理员账号 <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">个人信息</a></li>
                            <li><a href="#">设置</a></li>
                            <li><a href="#" id="logout">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="input-group">
            <span class="input-group-addon">
        <a href="#">
          <span class="glyphicon glyphicon-search"></span>
        </a>
            </span>
                        <input type="text" class="form-control" placeholder="搜点啥吧...">
                    </div>
                    <button type="submit" class="btn btn-default" >
                        搜索
                    </button>

                </form>
            </div>
        </div>
    </nav>
    <div style="padding-left: 82% ; ">
        <ul id="myTab" class="nav nav-tabs" >
            <li class="active">
                <a href="#bookPage" data-toggle="tab">
                    图书管理
                </a>
            </li>
            <li>
                <a href="#accountPage" data-toggle="tab">用户管理</a>
            </li>
        </ul>

    </div>

    <div id="myTabContent" class="tab-content" align="left">
        <div class="tab-pane fade in active" id="bookPage" style="margin-top: 10px">
            <table id="booktable"></table>
        </div>
        <div class="tab-pane fade" id="accountPage">
            <table id="accounttable"></table>
        </div>
    </div>
</div>
<div class="modal fade" id="bookModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">图书详细信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="bs-example bs-example-form" role="form" id="bookForm" action="#">
                    <div class="input-group">
                        <span class="input-group-addon" >编号</span>
                        <input type="text" name="id" id="id" class="form-control" placeholder="请输入书籍编号" >
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">版本号</span>
                        <input type="text" name="sbn" id="sbn" class="form-control" placeholder="请输入版本号"  >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">书名</span>
                        <input type="text" name="name" id="name" class="form-control" placeholder="请输入书名" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">作者</span>
                        <input type="text" name="author" id="author" class="form-control" placeholder="请输入作者" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">价格</span>
                        <input type="text" name="price" id="price" class="form-control" placeholder="请输入价格" >
                    </div>
                    <br>
                    <div >
                        <span>类型</span>
                        <!--<input type="text" class="form-control" placeholder="请输入类型">-->
                        <select class="selectpicker" name="type" id="type" multiple data-max-options="2" 	 maxOptionsText="最多不能超过两个！"  title="选择不超过两个描述词"  style="width: 200px;" >
                            <option >文学</option>
                            <option>编程</option>
                            <!--<option>Relish</option>-->
                        </select>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">创建时间</span>
                        <!--指定 date标记-->
                        <div class='input-group date' id='datetimepicker3'>
                            <input type='text' name="createTime" id="createTime" class="form-control" placeholder="请选择创建时间" />
                            <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">状态</span>
                        <input type="text" class="form-control" name="status"  id="status" placeholder="请输入状态" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">描述</span>
                        <textarea class="form-control" name="description" id="description"  placeholder="请输入描述" ></textarea>
                    </div>
                    <br>

                </form>
            </div>
            <!--</div>-->
            <div class="modal-footer" style="height: 100px;">
                <div >
                    <button type="button"  class="btn btn-primary" id="updatebook">修改</button>
                    <button type="button" class="btn btn-primary" id="resetForm" >重置</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="accountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="accountModalLabel">图书详细信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="bs-example bs-example-form" role="form" id="accountform" action="#">
                    <div class="input-group">
                        <span class="input-group-addon" >编号</span>
                        <input type="text" name="id" id="accountid" class="form-control" placeholder="请输入账号名" >
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">账户名</span>
                        <input type="text" name="name" id="accountname" class="form-control" placeholder="请输入爱好"  >
                    </div>
                    <br>
                    <div class="input-group">
                        <input type="password" name="password" id="password" class="form-control" style="visibility: hidden"  >
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">爱好</span>
                        <input type="text" name="hobby" id="hobby" class="form-control" placeholder="请输入爱好"  >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">签名</span>
                        <input type="text" name="signature" id="signature" class="form-control" placeholder="请输入该账户的签名" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">年龄</span>
                        <input type="number" name="age" id="age" class="form-control" placeholder="请输入年龄" >
                    </div>
                    <br>
                    <div >
                        <span>类型</span>
                        <!--<input type="text" class="form-control" placeholder="请输入类型">-->
                        <select class="selectpicker" name="type" id="accounttype" multiple data-max-options="2" 	 maxOptionsText="最多不能超过两个！"  title="请选择账号类型"  style="width: 200px;" >
                            <option ><%=Account.TYPE_TOURIST%></option>
                            <option><%=Account.TYPE_USER%></option>
                            <option><%=Account.TYPE_ADMINISTRATOR%></option>
                        </select>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">创建时间</span>
                        <!--指定 date标记-->
                        <div class='input-group date' id='datetimepicker4'>
                            <input type='text' name="createTime" id="accountcreateTime" class="form-control" placeholder="请选择创建时间" />
                            <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">状态</span>
                        <input type="text" class="form-control" name="status"  id="accountstatus" placeholder="请输入状态" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <label class="radio-inline">
                            <input type="radio"  value="男性" name="sex" checked="checked">男性
                        </label>
                        <label class="radio-inline">
                            <input type="radio"  value="女性" name="sex" >女性
                        </label>
                        <label class="radio-inline">
                            <input type="radio"  value="中性" name="sex">中性
                        </label>
                    </div>
                    <br>

                </form>
            </div>
            <!--</div>-->
            <div class="modal-footer" style="height: 100px;">
                <div >
                    <button type="button"  class="btn btn-primary" id="fixaccount">修改</button>
                    <button type="button" class="btn btn-primary" id="resetaccount">重置</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="newbookModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" >添加图书信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="bs-example bs-example-form" role="form" id="newbookForm" action="#">
                    <div class="input-group">
                        <span class="input-group-addon">版本号</span>
                        <input type="text" name="sbn"  class="form-control" placeholder="请输入版本号"  >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">书名</span>
                        <input type="text" name="name"  class="form-control" placeholder="请输入书名" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">作者</span>
                        <input type="text" name="author"  class="form-control" placeholder="请输入作者" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">价格</span>
                        <input type="text" name="price"  class="form-control" placeholder="请输入价格" >
                    </div>
                    <br>
                    <div >
                        <span>类型</span>
                        <!--<input type="text" class="form-control" placeholder="请输入类型">-->
                        <select class="selectpicker" name="type"  multiple data-max-options="2" 	 maxOptionsText="最多不能超过两个！"  title="选择不超过两个描述词"  style="width: 200px;" >
                            <option >文学</option>
                            <option>编程</option>
                            <!--<option>Relish</option>-->
                        </select>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">创建时间</span>
                        <!--指定 date标记-->
                        <div class='input-group date' id='datetimepicker5'>
                            <input type='text' name="createTime"  class="form-control" placeholder="请选择创建时间" />
                            <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">状态</span>
                        <input type="text" class="form-control" name="status"   placeholder="请输入状态" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">描述</span>
                        <textarea class="form-control" name="description"   placeholder="请输入描述" ></textarea>
                    </div>
                    <br>

                </form>
            </div>
            <!--</div>-->
            <div class="modal-footer" style="height: 100px;">
                <div >
                    <button type="button"  class="btn btn-primary" id="addnewbook">添加</button>
                    <button type="button" class="btn btn-primary" id="resetaddnewbookForm" >重置</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="newaccountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" >添加新用户</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="bs-example bs-example-form" role="form" id="newaccountform" action="#">
                    <div class="input-group">
                        <span class="input-group-addon">账户名</span>
                        <input type="text" name="name"  class="form-control" placeholder="请输入账户名"  required="required">
                    </div>
                    <br>
                    <div class="input-group">
                        <input type="password" name="password"  class="form-control" style="visibility: hidden" placeholder="请输入账户密码" required="required">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">爱好</span>
                        <input type="text" name="hobby"  class="form-control" placeholder="请输入爱好"  >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">签名</span>
                        <input type="text" name="signature"  class="form-control" placeholder="请输入该账户的签名" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">年龄</span>
                        <input type="number" name="age"  class="form-control" placeholder="请输入年龄" >
                    </div>
                    <br>
                    <div >
                        <span>类型</span>
                        <!--<input type="text" class="form-control" placeholder="请输入类型">-->
                        <select class="selectpicker" name="type"  multiple data-max-options="2" 	 maxOptionsText="最多不能超过两个！"  title="请选择账号类型"  style="width: 200px;"  required="required">
                            <option ><%=Account.TYPE_TOURIST%></option>
                            <option><%=Account.TYPE_USER%></option>
                            <option><%=Account.TYPE_ADMINISTRATOR%></option>
                        </select>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">创建时间</span>
                        <!--指定 date标记-->
                        <div class='input-group date' id='datetimepicker6'>
                            <input type='text' name="createTime"  class="form-control" placeholder="请选择创建时间"  required="required"/>
                            <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span >状态</span>
                        <select class="selectpicker" name="status"  multiple data-max-options="2" 	 maxOptionsText="最多不能超过两个！"  title="请选择账号状态"  style="width: 200px;" required="required" >
                            <option ><%=Account.STATUS_NORMAL%></option>
                            <option><%=Account.STATUS_ERRO%></option>
                        </select>
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <label class="radio-inline">
                            <input type="radio"  value="男性" name="sex" checked="checked">男性
                        </label>
                        <label class="radio-inline">
                            <input type="radio"  value="女性" name="sex" >女性
                        </label>
                        <label class="radio-inline">
                            <input type="radio"  value="中性" name="sex">中性
                        </label>
                    </div>
                    <br>

                </form>
            </div>
            <!--</div>-->
            <div class="modal-footer" style="height: 100px;">
                <div >
                    <button type="button"  class="btn btn-primary" id="newaccount">添加</button>
                    <button type="button" class="btn btn-primary" id="resetnewaccountForm">重置</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="booktoolbar" class="toolbar">
    <button type="button" id="addNewBookBtn" class="btn btn-primary btn-sm"  data-toggle="modal" data-target="#newbookModal">添加新书</button>
    <button type="button" id="deleteBookBtn" class="btn btn-danger btn-sm" >删除选中</button>
</div>
<div id="accounttoolbar" class="toolbar">
    <button type="button" id="addNewaccounBtn" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#newaccountModal" >添加新账号</button>
    <button type="button" id="deleteaccounBtn" class="btn btn-danger btn-sm" >删除选中</button>
</div>
<script>
    function bookoperateFormatter (value, row, index) {//赋予的参数
        return [
            '<button  id="updateOpperation"   class="btn btn-info btn-sm rightSize detailBtn" type="button" data-toggle="modal" data-target="#bookModal"><i class="fa fa-paste"></i> 修改</button>',
            '<button id="removebook"  class="btn btn-danger btn-sm rightSize packageBtn" type="button"><i class="fa fa-envelope"></i>删除</button>'
        ].join('');
    };
    window.operateEvents = {
        "click #updateOpperation" : function(e, value, row, index) {   //编辑
            $('#bookModal').on('show.bs.modal', function () {
                    $('#id').val(row.id);
                    $('#status').val(row.status);
                    $('#description').val(row.description);
                    $('#sbn').val(row.sbn);
                    $('#createTime').val(row.createTime);
                    $('#name').val(row.name);
                    $('#author').val(row.author);
                    $('#type').val(row.type);
                    $('#price').val(row.price);
                    $('.selectpicker').val(row.type);
                    $('.selectpicker').selectpicker('render');
                    //重新加载数据

            })
            $('#resetForm').click(function () {
                $('#id').val(row.id);
                $('#status').val(row.status);
                $('#description').val(row.description);
                $('#sbn').val(row.sbn);
                $('#createTime').val(row.createTime);
                $('#name').val(row.name);
                $('#author').val(row.author);
                $('#type').val(row.type);
                $('#price').val(row.price);
                $('.selectpicker').val(row.type);
                $('.selectpicker').selectpicker('render');
            });
        },
        "click #removebook" : function(e, value, row, index) {   //删除
                BootstrapDialog.confirm({
                    size:BootstrapDialog.SIZE_SMALL,
                    title: '提示框',
                    message: '确认删除?',
                    type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                    closable: true, // <-- Default value is false
                    draggable: true, // <-- Default value is false
                    btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                    btnOKLabel: '确认', // <-- Default value is 'OK',
                    btnOKClass: 'btn-warning',// <-- If you didn't specify it, dialog type will be used,
                    callback: function(result) {
                        // result will be true if button was click, while it will be false if users close the dialog directly.
                        if(result) {
                                //发送请求
                                $.ajax({
                                    type:'get',
                                    dataType:'json',
                                    url:'${pageContext.request.contextPath}/removeBookServlet?id='+row.id,
                                })
                               
                        }
                    }
                });
            //重新加载数据
            $('#booktable').bootstrapTable('refresh');
        },


    }
    $('#booktable').bootstrapTable({
        method:'post',
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        url: '${pageContext.request.contextPath}/bookListServlet',
        toolbar:"#booktoolbar", //设置自定义工具栏容器ID，也可以是容器样式类名.toolbar
        pagination: true,
        height: document.body.clientHeight-145,   //动态获取高度值，可以使表格自适应页面
        showPaginationSwitch:true,         //	是否显示 数据条数选择框
        cache: false,                       // 不缓存
        striped: true,                     //是否显示行间隔色
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        search: true,                     //显示搜索框
        showRefresh: true,                  //是否显示刷新按钮
        showPaginationSwitch: true,       //是否显示选择分页数按钮
        // showButtonIcons:false,            //是否显示分页
        paginationNextText:"下一页",
        paginationPreText:"上一页",
        paginationHAlign:'left' ,
        paginationLoop:false,
        // searchOnEnterKey:true,          //按下回车键开始搜索
        showColumns:true,                  //显示列
        pageNumber:1,
        dataType:'json',
        sidePagination: 'server',
        trimOnSearch:true,
        queryParams:RequeryParams, //参数
        columns: [
            {
                checkbox: true,
                visible: true                  //是否显示复选框
            },
            {
                searchable:false,
                field: 'tableid',
                title: 'ID'
            }, {
                searchable:false,
                field: 'id',
                visible:false,

            }, {
                field: 'name',
                title: 'Name'
            }, {
                field: 'author',
                title: '作者'
            }, {
                field: 'price',
                title: '价格'
            }, {
                field: 'type',
                title: '类型'
            }
            , {
                field: 'sbn',
                title: '版本号'
            }
            , {
                field: 'createTime',
                title: '添加书籍时间'
            }
            , {
                field: 'description',
                title: '简介'
            }, {
                field: 'operation',
                title: '操作',
                formatter:bookoperateFormatter,
                events:operateEvents
            }
            ]
    })
    function RequeryParams(params) { //配置参数
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.limit, //页面大小
            pageNumber: params.pageNumber, //页码
            offset : params.offset, // SQL语句偏移量
            "tableSortName": params.sort,  //表格的排序
            "tableSearch": params.search,  //表格的搜索框的内容
            "tableOrder": params.order     //排序方式
        };
        return temp;
    }

</script>
<script>
    function accountoperation(value, row, index) {//赋予的参数
        return [
            '<button   id="updateaccount"  class="btn btn-info btn-sm rightSize " type="button" data-toggle="modal" data-target="#accountModal"><i class="fa fa-paste"></i> 修改</button>',
            '<button  id="removeaccount"  class="btn btn-danger btn-sm rightSize" type="button"><i class="fa fa-envelope"></i>删除</button>'
        ].join('');
    }
    window.accountoperationEvent={
        "click #updateaccount":function (e, value, row, index) {
            $('#accountModal').on('show.bs.modal', function () {
                $('#accountid').val(row.id);
                $('#accountname').val(row.name);
                $('#password').val(row.password);
                $('#accountcreateTime').val(row.createTime);
                $('#accountstatus').val(row.status);
                $('#hobby').val(row.hobby);
                $('#age').val(row.age);
                $('#signature').val(row.signature);
                $('input[name="sex"]').each(function(){
                    if ($(this).val()==row.sex){
                        $(this).attr('checked','checked');
                    }
                });
                //默认选择账户类型
                $('.selectpicker').val(row.type);
                $('.selectpicker').selectpicker('render');
            })
            $('#resetaccount').click(function (row) {
                $('#accountid').val(row.id);
                $('#accountname').val(row.name);
                $('#accountcreateTime').val(row.createTime);
                $('#accountstatus').val(row.status);
                $('#hobby').val(row.hobby);
                $('#age').val(row.age);
                $('#signature').val(row.signature);
                $('input[name="sex"]').each(function(){
                    if ($(this).val()==row.sex){
                        $(this).attr('checked','checked');
                    }
                });
                //默认选择账户类型
                $('.selectpicker').val(row.type);
                $('.selectpicker').selectpicker('render');
            });
        // })
        },
        "click #removeaccount":function (e, value, row, index) {
            BootstrapDialog.confirm({
                size:BootstrapDialog.SIZE_SMALL,
                title: '提示框',
                message: '确认删除?',
                type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                closable: true, // <-- Default value is false
                draggable: true, // <-- Default value is false
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                btnOKClass: 'btn-warning',// <-- If you didn't specify it, dialog type will be used,
                callback: function(result) {
                    // result will be true if button was click, while it will be false if users close the dialog directly.
                    if(result) {
                        //发送请求
                        $.ajax({
                            type:'get',
                            dataType:'json',
                            url:'${pageContext.request.contextPath}/removeAccountServlet?id='+row.id,
                        })

                    }
                }
            });
            //重新加载数据
            $('#booktable').bootstrapTable('refresh');
        }
    }
    $('#accounttable').bootstrapTable({
        method:'post',
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        url: '${pageContext.request.contextPath}/accountListServlet',
        toolbar:"#accounttoolbar", //设置自定义工具栏容器ID，也可以是容器样式类名.toolbar
        pagination: true,
        height: document.body.clientHeight-145,   //动态获取高度值，可以使表格自适应页面
        showPaginationSwitch:true,         //	是否显示 数据条数选择框
        cache: false,                       // 不缓存
        striped: true,                     //是否显示行间隔色
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        search: true,                     //显示搜索框
        showRefresh: true,                  //是否显示刷新按钮
        // showPaginationSwitch: true,     //是否显示选择分页数按钮
        // showButtonIcons:false,            //是否显示分页
        paginationNextText:"下一页",
        paginationPreText:"上一页",
        paginationHAlign:'left' ,
        paginationLoop:false,
        // searchOnEnterKey:true,          //按下回车键开始搜索
        showColumns:true,                  //显示列
        pageNumber:1,
        dataType:'json',
        sidePagination: 'server',
        trimOnSearch:true,
        queryParams:RequeryParams, //参数
        columns: [
            {
                checkbox: true,
                visible: true                  //是否显示复选框
            },
            {
                searchable:false,
                field: 'tableid',
                title: 'ID'
            }, {
                searchable:false,
                field: 'id',
                visible:false
            }, {
                field: 'name',
                title: '账号名'
            }, {
                field: 'password',
                visible:false
            }, {
                field: 'sex',
                title: '性别'
            }, {
                field: 'age',
                title: '年龄'
            }, {
                field: 'createTime',
                title: '创建账号时间'
            }
            , {
                field: 'status',
                title: '状态'
            }
            , {
                field: 'hobby',
                title: '爱好'
            } , {
                field: 'signature',
                title: '签名'
            }
            , {
                field: 'operation',
                title: '操作',
                formatter: accountoperation,
                events:accountoperationEvent
            }

        ]
    })
    function RequeryParams(params) { //配置参数
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.limit, //页面大小
            pageNumber: params.pageNumber, //页码
            offset : params.offset, // SQL语句偏移量
            "tableSortName": params.sort,  //表格的排序
            "tableSearch": params.search,  //表格的搜索框的内容
            "tableOrder": params.order     //排序方式
        };
        return temp;
    }
</script>

<script>
    $(function () {
        $('#updatebook').on('click',function () {
            BootstrapDialog.confirm({
                size:BootstrapDialog.SIZE_SMALL,
                title: '提示框',
                message: '确认删除?',
                type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                closable: true, // <-- Default value is false
                draggable: true, // <-- Default value is false
                btnCancelLabel: '取消!', // <-- Default value is 'Cancel',
                btnOKLabel: '确认!', // <-- Default value is 'OK',
                btnOKClass: 'btn-warning',// <-- If you didn't specify it, dialog type will be used,
                callback: function(result) {
                    // result will be true if button was click, while it will be false if users close the dialog directly.
                    if(result) {
                        $.ajax({
                            url : "<%=request.getContextPath()%>/updateBookServlet",
                            type : "post",
                            dataType : "json",
                            data : $("#bookForm").serialize(),
                            success : function (data){
                                alert(data.msg);
                                $('#booktable').bootstrapTable('refresh');
                            },
                            error:function(){
                                alert(data.erro);
                            }
                        });
                    }
                }
            });

        });
        
        $('#fixaccount').on('click',function () {
            BootstrapDialog.confirm({
                size:BootstrapDialog.SIZE_SMALL,
                title: '提示框',
                message: '确认更新?',
                type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                closable: true, // <-- Default value is false
                draggable: true, // <-- Default value is false
                btnCancelLabel: '取消!', // <-- Default value is 'Cancel',
                btnOKLabel: '确认!', // <-- Default value is 'OK',
                btnOKClass: 'btn-warning',// <-- If you didn't specify it, dialog type will be used,
                callback: function(result) {
                    // result will be true if button was click, while it will be false if users close the dialog directly.
                    if(result) {
                        $.ajax({
                            url : "<%=request.getContextPath()%>/updateAccountServlet",
                            type : "post",
                            dataType : "json",
                            data : $("#accountform").serialize(),
                            success : function (data){
                                alert(data.msg);
                                $('#accounttable').bootstrapTable('refresh');
                            },
                            error:function(){
                                alert(data.error);
                            }
                        });
                    }
                }
            });

        });
    })
  
</script>
<script>
$(function () {
    //给删除选中书籍的按钮注册事件
    $('#deleteBookBtn').click(function () {
        BootstrapDialog.confirm({
            size:BootstrapDialog.SIZE_SMALL,
            title: '提示框',
            message: '确认删除?',
            type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
            closable: true, // <-- Default value is false
            draggable: true, // <-- Default value is false
            btnCancelLabel: '取消!', // <-- Default value is 'Cancel',
            btnOKLabel: '确认!', // <-- Default value is 'OK',
            btnOKClass: 'btn-warning',// <-- If you didn't specify it, dialog type will be used,
                 callback: function(result) {
                     // result will be true if button was click, while it will be false if users close the dialog directly.
                     if(result) {
                         var rows = $('#booktable').bootstrapTable('getSelections');
                         if (rows==null||rows.length==0){
                             confirm("您尚未选择要删除的书籍");
                             return;
                         }
                         var id='';
                         // alert(rows.length);
                         $.each(rows,function (index,row) {
                             if (index==rows.length-1){
                                 id=id+'id='+row.id;
                             }else {
                                 id=id+'id='+row.id+"&";
                             }
                         })
                         $.ajax({
                             url:'${pageContext.request.contextPath}/removeBookServlet?'+id,
                             type : "post",
                             dataType : "json",
                             success : function (data){
                                 alert(data.msg);
                                 $('#booktable').bootstrapTable('refresh');
                             },
                             error:function(){
                                 alert("发送删除请求失败！");
                             }
                         })
                     }
                 }
        });
    });
    $('#addnewbook').click(function () {
        BootstrapDialog.confirm({
            size:BootstrapDialog.SIZE_SMALL,ei
            title: '提示框',
            message: '确认添加?',
            type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
            closable: true, // <-- Default value is false
            draggable: true, // <-- Default value is false
            btnCancelLabel: '取消!', // <-- Default value is 'Cancel',
            btnOKLabel: '确认!', // <-- Default value is 'OK',
            btnOKClass: 'btn-warning',// <-- If you didn't specify it, dialog type will be used,
            callback: function(result) {
                // result will be true if button was click, while it will be false if users close the dialog directly.
                if(result) {
                    $.ajax({
                        url:'${pageContext.request.contextPath}/addNewBookServlet',
                        type : "post",
                        dataType : "json",
                        data : $("#newbookForm").serialize(),
                        success : function (data){
                            alert(data.msg);
                            $('#booktable').bootstrapTable('refresh');
                        },
                        error:function(){
                            alert("发送添加新书请求失败！");
                        }
                    })
                }
            }
        });

    });
    $('#resetnewaccountForm').click(function () {
        document.getElementById("newbookForm").reset();
    });
})

</script>
<script>
    $(function () {
        //给删除选中用户的按钮注册事件
        $('#deleteaccounBtn').click(function () {
            BootstrapDialog.confirm({
                size:BootstrapDialog.SIZE_SMALL,
                title: '提示框',
                message: '确认删除?',
                type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                closable: true, // <-- Default value is false
                draggable: true, // <-- Default value is false
                btnCancelLabel: '取消!', // <-- Default value is 'Cancel',
                btnOKLabel: '确认!', // <-- Default value is 'OK',
                btnOKClass: 'btn-warning',// <-- If you didn't specify it, dialog type will be used,
                callback: function(result) {
                    // result will be true if button was click, while it will be false if users close the dialog directly.
                    if(result) {
                        var rows = $('#accounttable').bootstrapTable('getSelections');
                        if (rows==null||rows.length==0){
                            confirm("您尚未选择要删除的用户");
                            return;
                        }
                        var id='';
                        $.each(rows,function (index,row) {
                            if (index==rows.length-1){
                                id=id+'id='+row.id;
                            }else {
                                id=id+'id='+row.id+"&";
                            }
                        })
                        $.ajax({
                            url:'${pageContext.request.contextPath}/removeAccountServlet?'+id,
                            type : "post",
                            dataType : "json",
                            success : function (data){
                                alert(data.msg);
                                $('#accounttable').bootstrapTable('refresh');
                            },
                            error:function(){
                                alert("发送删除请求失败！");
                            }
                        })
                    }
                }
            });

        });

        //添加新账号的提交请求
        $('#newaccount').click(function () {
            $.ajax({
                url:'${pageContext.request.contextPath}/addNewAccountServlet',
                type : "post",
                dataType : "json",
                data : $("#newaccountform").serialize(),
                success : function (data){
                    alert(data.msg);
                    $('#accounttable').bootstrapTable('refresh');
                },
                error:function(){
                    alert("发送添加新用户请求失败！");
                }
            })
        });
        //重置添加新账号的form
        $('#resetnewaccountForm').click(function () {
            document.getElementById("newaccountform").reset();
        });
    })
</script>
<script>
    $('#datetimepicker3').datetimepicker({
        language:  'zh-CN',
        todayBtn:true,
        format: 'yyyy-mm-dd hh:mm:ss',
        autoclose:true
    });
    $('#datetimepicker4').datetimepicker({
        language:  'zh-CN',
        todayBtn:true,
        format: 'yyyy-mm-dd hh:mm:ss',
        autoclose:true
    });
    $('#datetimepicker5').datetimepicker({
        language:  'zh-CN',
        todayBtn:true,
        format: 'yyyy-mm-dd hh:mm:ss',
        autoclose:true
    });
    $('#datetimepicker6').datetimepicker({
        language:  'zh-CN',
        todayBtn:true,
        format: 'yyyy-mm-dd hh:mm:ss',
        autoclose:true

    });
</script>>
<script>
    $(function () {
        //给退出登录添加点击事件
        $('#logout').click(function () {
            BootstrapDialog.confirm({
                size:BootstrapDialog.SIZE_SMALL,
                title: '提示框',
                message: '确认退出登录?',
                type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                closable: true, // <-- Default value is false
                draggable: true, // <-- Default value is false
                btnCancelLabel: '取消!', // <-- Default value is 'Cancel',
                btnOKLabel: '确认!', // <-- Default value is 'OK',
                btnOKClass: 'btn-warning', // <-- If you didn't specify it, dialog type will be used,
                callback: function(result) {
                    // result will be true if button was click, while it will be false if users close the dialog directly.
                    if(result) {
                        window.location.href="${pageContext.request.contextPath}/booksystem.jsp";
                    }
                }
            });
        });
    });

</script>


</body>
</html>

