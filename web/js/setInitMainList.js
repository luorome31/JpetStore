$(document).ready(function () {
    // 获取按钮组元素
    var buttonGroup = $('.buttonGroup');

    // 添加点击事件监听器
    buttonGroup.on('click', 'button', function () {
        // 获取按钮的值
        console.log($(this).text().trim());
        var buttonValue = $(this).text().trim();

        // 发起 AJAX 请求
        $.ajax({
            url: 'http://localhost:8080/JpetStore/mainList?categoryId=' + buttonValue,
            method: 'GET',  // 如果是获取数据，通常使用 GET 请求
            success: function (data) {
                // 处理服务器返回的数据
                updateMainShow(data);
            },
            error: function (xhr, status, error) {
                console.error('发生错误：', error);
            }
        });
    });

    // 更新主界面显示
    function updateMainShow(data) {
        // 获取主界面元素
        var mainShow = $('.mainShow');

        // 清空主界面内容
        mainShow.empty();
        let dataJson = JSON.parse(data);
        let mainList = dataJson.mainList;
        mainList.forEach(function (item){
            // 生成新的主界面元素
            var newMainShow = '<div class="col-md-3">\n' +
                '    <div class="card border-success mb-3">\n' +
                '        <a href="itemForm?itemId=' + item.itemId + '" class="productImage">\n' +
                '            ' + item.description + '\n' +
                '        </a>\n' +
                '        <div class="card-body">\n' +
                '            <p class="card-text">' + item.itemId + '</p>\n' +
                '            <p class="card-text">price: $' + item.listPrice + '</p>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>';


            // 添加到主界面中
            mainShow.append(newMainShow);
        })

        }
});
