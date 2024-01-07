$(document).ready(function() {
    let itemId = $('#itemIdRef').text();
    console.log(itemId);
    $('.itemNumber').on('change', function() {
        let value = $(this).val();
        let itemId = $(this).attr('name');
        let singlePrice = $(`#${itemId}.singlePrice`).text();
        let valueWithoutDollar = singlePrice.replace("$", "");
        let oldPrice = $(`#${itemId}.totalPrice`).text();
        let oldPriceWithoutDollar = oldPrice.replace("$", "");

        console.log(singlePrice)
        console.log(value);
        console.log(itemId);
        $.ajax({
            url: 'http://localhost:8080/JpetStore/quantityChange?number='+value+'&itemId='+ itemId,
            method: 'GET',
            dataType: 'json',
            success: function(response) {
                if (response.message === 'success') {
                    $('#informMessage').text('修改成功');
                    $(`#${itemId}.totalPrice`).text("$"+value * valueWithoutDollar);
                    $('#subTotal').text("Sub Total: "+"$"+(parseFloat($('#subTotal').text().split(': ')[1].replace('$', '')) - parseFloat(oldPriceWithoutDollar) + parseFloat(value * valueWithoutDollar)));
                } else {
                    $('#informMessage').text('修改失败');
                }

                setTimeout(function() {
                    $('#informMessage').empty();
                }, 2000);
            },
            error: function() {
                $('#informMessage').text('请求失败');

                setTimeout(function() {
                    $('#informMessage').empty();
                }, 2000);
            }
        });
    });
    $('#cartConfirm').on('shown.bs.modal', function () {
        var totalPrice = $('#subTotal').text().split(':')[1].trim();
        $('#ConfirmPrice').text(totalPrice);
    });

    $(function() {
        //submitBtn的点击事件
        $("#submitBtn").click(function () {
            //页面重定位到myOrder
            window.location.href="myOrder";
        });
    });
});
