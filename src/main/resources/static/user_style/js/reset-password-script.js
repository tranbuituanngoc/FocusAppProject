$(document).ready(function () {
    if(token !=null){
        $('form').on('submit', function (event) {
            event.preventDefault();

            $.ajax({
                type: 'GET',
                url: '/auth/handle-reset-password',
                data: {
                    newPassword: $('#password').val(),
                    token: token
                },
                contentType: 'application/json',
                success: function (data) {
                    const type = data.left;
                    if (type === "success") {
                        Swal.fire({
                            icon: "success",
                            title: "Thành công!",
                            text: data.right,
                            confirmButtonText: 'Tiếp tục',
                            customClass: {
                                confirmButton: 'confirm-button-class',
                                cancelButton: 'cancel-button-class',
                                title: 'title-alert-class',
                            },
                        }).then(() => {
                            setTimeout(function () {
                                window.location.href = '/auth/login';
                            }, 5000);
                        });
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Thất bại!",
                            text: data.right,
                            confirmButtonText: 'Thử lại',
                            customClass: {
                                confirmButton: 'confirm-button-class',
                                cancelButton: 'cancel-button-class',
                                title: 'title-alert-class',
                            },
                        });
                    }
                },
                error: function (data) {
                    console.error(data)
                }
            })

        });
    }else {
        Swal.fire({
            icon: "error",
            title: "Thất bại!",
            text: "Token không hợp lệ, bạn sẽ được chuyển hướng về trang đăng nhập sau 5 giây!",
            customClass: {
                confirmButton: 'confirm-button-class',
                cancelButton: 'cancel-button-class',
                title: 'title-alert-class',
            },
        }).then(() => {
            setTimeout(function () {
                window.location.href = '/auth/login';
            }, 5000);
        });

    }
});