$(document).ready(function() {
    var stage = 1;
    $('.confirmButton').on('click', function() {
        // 发起异步通信
        $.ajax({
            url: 'http://localhost:8080/JpetStore/newOrder?stage='+stage,
            method: 'GET',
            success: function(response) {
                // 根据服务器返回的 stage 值进行操作
                if (response.stage === 2) {
                    $('#div1').hide();
                    $('#div2').show();
                    $('#div3').hide();
                    $(".icon2:eq(0)").css("color", "black");
                    $(".icon2:eq(1)").css("color", "blue");
                    stage = 2
                } else if (response.stage === 3) {
                    $('#div1').hide();
                    $('#div2').hide();
                    $('#div3').show();
                    $(".icon2:eq(1)").css("color", "black");
                    $(".icon2:eq(2)").css("color", "blue");
                    stage=3
                }
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
});