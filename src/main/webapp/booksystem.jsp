<%--
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
    <title>图书主界面</title>
</head>
<body>
<div>


    <nav class="navbar navbar-default" role="navigation" style="background: #393D49;">
        <div class="container-fluid">
            <div class="navbar-header" style="margin-left: 40%;">
                <a class="navbar-brand" href="#" style="color: #01AAED;">图书管理用户主页面</a>
            </div>
            <div>
<%--                <!--向右对齐-->--%>
<%--                <ul class="nav navbar-nav navbar-right">--%>
<%--                    <li class="dropdown">--%>
<%--                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
<%--                            我的账户 <b class="caret"></b>--%>
<%--                        </a>--%>
<%--                        <ul class="dropdown-menu">--%>
<%--                            <li><a href="#">个人信息</a></li>--%>
<%--                            <li><a href="#">设置</a></li>--%>
<%--                            <li><a href="#">我的借阅记录</a></li>--%>
<%--                            <li class="divider"></li>--%>
<%--                            <li><a href="#">分离的链接</a></li>--%>
<%--                            <li class="divider"></li>--%>
<%--                            <li><a href="#">另一个分离的链接</a></li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                </ul>--%>
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
                 <button type="button" class="btn btn-default" onclick="javascript:window.location.href='${pageContext.request.contextPath}/login.jsp'">
                        登录
                  </button>
               <button type="button" class="btn btn-default" onclick="javascript:window.location.href='${pageContext.request.contextPath}/accountregister.jsp'">
                  注册
              </button>
                </form>
            </div>
        </div>
    </nav>
    <div style="padding-left: 82% ; ">
        <ul id="myTab" class="nav nav-tabs" >
            <li class="active">
                <a href="#home" data-toggle="tab">
                    图书资源
                </a>
            </li>
            <li><a href="#ios" data-toggle="tab">图书资讯</a></li>
        </ul>

    </div>

    <div id="myTabContent" class="tab-content" align="left">
        <div class="tab-pane fade in active" id="home" style="margin-top: 10px">
            <table id="booktable"></table>
        </div>
        <div class="tab-pane fade" id="ios">
            <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
                TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
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
                        <input type="text" name="sbn" id="sbn" class="form-control" placeholder="暂无版本号"  >
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
                        <span class="input-group-addon">类型</span>
                        <input type="text" class="form-control" name="status"  id="type" placeholder="暂无类型" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">创建时间</span>
                         <input type="text" class="form-control" name="status"  id="createTime" placeholder="暂无创建时间" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">状态</span>
                        <input type="text" class="form-control" name="status"  id="status" placeholder="暂无状态" >
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">描述</span>
                        <textarea class="form-control" name="description" id="description"  placeholder="暂无描述" ></textarea>
                    </div>
                    <br>

                </form>
            </div>
            <!--</div>-->
            <div class="modal-footer" style="height: 100px;">

            </div>
        </div>
    </div>
</div>

<script>
    function bookoperateFormatter (value, row, index) {//赋予的参数
        return [
            '<button  id="detailBtn"   class="btn btn-info btn-sm rightSize " type="button" data-toggle="modal" data-target="#bookModal"><i class="fa fa-paste"></i> 详细</button>',
            '<button id="borrowBtn"  class="btn btn-danger btn-sm rightSize " type="button"><i class="fa fa-envelope"></i>借阅</button>'
        ].join('');
    };
    window.operateEvents = {
        "click #detailBtn" : function(e, value, row, index) {   //查看仔细
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
                $('#age').val(row.age);
            })
        },
        "click #borrowBtn" : function(e, value, row, index) {   //借阅
            BootstrapDialog.confirm({
                size:BootstrapDialog.SIZE_SMALL,
                title: '借阅提示框',
                message: '确认借阅?',
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
                        <%--window.location.href="${pageContext.request.contextPath}/borrowBookServlet?bookid="+row.id;--%>
                        $.ajax({
                            type:'get',
                            dataType:'json',
                            url:'${pageContext.request.contextPath}/borrowBookServlet?bookid='+row.id,
                            success:function (data) {
                                window.alert(data.msg);
                            }
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
</body>
</html>

