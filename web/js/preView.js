
    $(document).ready(function () {
        $('.productImage').hover(
            function (event) {
                const hrefAttr = $(this).attr("href");

                const itemId = hrefAttr.split('?')[1];
                showProductPreview(itemId, event);
            },
            function () {
                hideProductPreview();
            }
        );
    });
    function showProductPreview(itemId, event) {
        // 发送异步请求获取商品信息
        // 假设有一个服务端API返回商品信息

        $.ajax({
            url: "http://localhost:8080/JpetStore/preView?" + itemId,
            type: "GET",
            dataType: "json",
            success: function (productInfo) {
                // 显示预览框，并设置位置
                const previewBox = createPreviewBox(productInfo);
                setPosition(event, previewBox);
                $('body').append(previewBox);
            },
            error: function (error) {
                console.error('Error fetching product info:', error);
            }
        });

    }

    function hideProductPreview() {
        // 隐藏预览框
        $('.product-preview').remove();
    }

    function createPreviewBox(productData) {
        // 创建预览框的 DOM 元素
        const previewBox = $('<div class="product-preview text-muted text-center text-capitalize" style="background: #ffffff;z-index: 1"></div>');
        previewBox.html(`
                <h5>${productData.description}</h5>
                    <p>${productData.name}</p>
                    <p>Quantity: ${productData.quantity}</p>
            `);
        return previewBox;
    }

    function setPosition(event, element) {
        const mouseX = event.pageX;
        const mouseY = event.pageY;

        const elementWidth = element.outerWidth();
        const elementHeight = element.outerHeight();

        const windowWidth = $(window).width();
        const windowHeight = $(window).height();

        // 防止预览框超出窗口边界
        const maxX = windowWidth - elementWidth;
        const maxY = windowHeight - elementHeight;

        // 设置预览框的位置
        const posX = mouseX > maxX ? maxX : mouseX;
        const posY = mouseY > maxY ? maxY : mouseY;

        element.css({
            position: 'absolute',
            top: posY,
            left: posX
        });
    }