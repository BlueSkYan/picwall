<!DOCTYPE HTML>
<!--
	Multiverse by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>PicturesWall by Yan</title>
    <meta charset="utf-8"/>
    <meta name="referrer" content="no-referrer">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/assets/css/main.css"/>
    <link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.min.css"/>

    <noscript>
        <link rel="stylesheet" href="/assets/css/noscript.css"/>
    </noscript>
</head>
<body class="is-preload">
<script src="/layui/layui.js"></script>
<!-- Wrapper -->
<div id="wrapper">

    <!-- Header -->
    <header id="header">
        <h1><a href="/"><strong>PicturesWall</strong></a></h1>
        <nav>
            <ul>
                <li><a href="#footer" class="icon solid fa-info-circle">About</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->
    <div id="main">
        <#list picInfoList as picInfo>
            <article class="thumb">
                <a href="${picInfo.fullsLocation}" class="image"><img src="${picInfo.thumbsLocation}" alt=""/></a>
                <h2>${picInfo.title}</h2>
                <p>${picInfo.description}</p>
            </article>
        </#list>
    </div>
    <!-- Footer -->
    <footer id="footer" class="panel">
        <div class="inner split">
            <div>
                <section>
                    <h2>Hey.</h2>
                    <p>There are all kinds of people in this world. It happens that we have become friends. This is not fate. It is just that we should be friends.
                        <br>这世界上有各种各样的人，恰巧我们成为了朋友，这不是缘分，只仅仅是我们本就应该是朋友。</p>
                </section>
                <section>
                    <h2>Follow me on ...</h2>
                    <ul class="icons">
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1575844877&site=qq&menu=yes"
                               class="icon brands"><i class="fa fa-qq fa-2x" aria-hidden="true"></i></a></li>
                        <li><a class="icon brands" href="https://github.com/BlueSkYan"><i class="fa fa-github fa-2x"
                                                                                          aria-hidden="true"></i></a>
                        </li>
                    </ul>
                </section>
                <p class="copyright">
                    &copy; Unttled. Design: Yan.
                </p>
            </div>
            <div>
                <section>
                    <h2>上传照片</h2>
                    <form action="upload/text" method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
                        <div class="fields">
                            <div class="field half">
                                <input type="text" name="title" id="title" placeholder="照片标题" required/>
                            </div>
                            <div class="field half">
<#--                                <input type="file" name="file" id="file" placeholder="选择照片" accept="image/*" required/>-->
                                <button type="button" id="file" class="primary" name="file">
                                    <i class="layui-icon">&#xe67c;</i>上传图片
                                </button>
                            </div>
                            <div class="field">
                                <textarea name="description" id="description" rows="4" placeholder="照片描述"
                                          required></textarea>
                            </div>
                        </div>
                        <ul class="actions">
                            <li><input type="submit" value="上传" class="primary"/></li>
                            <li><input type="reset" value="重置"/></li>
                        </ul>
                        <input type="text" id="fullsVirtualPath"  name="fullsVirtualPath" style="display: none">
                        <input type="text" id="thumbsVirtualPath"  name="thumbsVirtualPath" style="display: none">
                    </form>
                </section>
            </div>
        </div>
    </footer>
</div>
<script>
    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#file' //绑定元素
            ,url: '/upload/image' //上传接口
            ,accept: 'images'
            ,acceptMime: 'image/*'
            ,done: function(res){
                //上传完毕回调
                layer.msg(res.msg);
                $("#fullsVirtualPath").val(res.data['fullsVirtualPath'])
                $("#thumbsVirtualPath").val(res.data['thumbsVirtualPath']);
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });
    function checkForm(){
        if ($('#fullsVirtualPath').val()==''||$('#thumbsVirtualPath').val()==''){
            layer.msg("请上传图片");
            return false;
        }else {
            return true;
        }
    }
</script>
<!-- Scripts -->
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/jquery.poptrox.min.js"></script>
<script src="/assets/js/browser.min.js"></script>
<script src="/assets/js/breakpoints.min.js"></script>
<script src="/assets/js/util.js"></script>
<script src="/assets/js/main.js"></script>

</body>
</html>