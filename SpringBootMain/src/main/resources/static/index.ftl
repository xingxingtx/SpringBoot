<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<#assign _ctx=request.contextPath />
<body>
<img th:src="@{/getKaptchaImage}" width="80" height="34" class="captcha changeCaptcha pull-left margin-r-5" alt="验证码"/>
<a href="javascript:void(0)" class="changeCaptcha">换一张</a>

    <script>
        //切换验证码
        $(document).on('click', '.changeCaptcha', function () {
            var url = _ctx + "/getKaptchaImage";
            $(".captcha").attr("src", url);
        })
    </script>
</body>
</html>