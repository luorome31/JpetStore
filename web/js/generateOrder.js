
$(document).ready(function () {
    $('#fillOutBtn').click(function () {
        $('a[href="#div2"]').tab('show');

    });
    $('#confirmBtn').click(function () {
        $('a[href="#div3"]').tab('show');
        //禁用
        $('#tab1').addClass('disabled')
        $('#tab2').addClass('disabled')
        $.ajax({
            url: 'http://localhost:8080/JpetStore/newOrder?stage='+2,
            method: 'GET',
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                console.error(error);
            }
        });
    });

    //shippingAddress勾选框勾选则显示模态框
    $('#shippingAddress').click(function () {
        $('#shippingCheck').modal('show');
    });
    //closeBtn点击后则将shippingAddress勾选框取消勾选
    $('#closeBtn').click(function () {
        $('#shippingAddress').prop('checked',false);
    });

});