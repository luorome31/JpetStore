$(document).ready(function() {
    let itemId = $('#itemIdRef').text();
    console.log(itemId);
    $('.itemNumber').on('change', function() {
        let value = $(this).val();
        let itemId = $(this).attr('name');
        console.log(value);
        console.log(itemId);
        $.ajax({
            url: 'http://localhost:8080/JpetStore/quantityChange?number='+value+'&itemId='+ itemId,
            method: 'GET',
            dataType: 'json',
            success: function(response) {
                if (response.message === 'success') {
                    $('#informMessage').text('修改成功');
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
});
