
$(document).ready(function () {
    $('form').on('submit', function (e) {
        e.preventDefault();

        const password = $('#password').val();
        const rePassword = $('#re-password').val();

        const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z\d]).{8,}$/gm;

        let isValid = true;


        if (!passwordRegex.test(password)) {
            $('#error-password').html('Mật khẩu phải có tối thiểu 8 ký tự và phải bao gồm<br> 1 kí tự in hoa, 1 kí tự in thường và 1 chữ số');
            isValid = false;
            console.log('password failed')
        } else {
            $('#error-password').text('');
        }

        if (password !== rePassword) {
            $('#error-repassword').text('Mật khẩu nhập lại không khớp');
            isValid = false;
            console.log('repassword  failed')
        } else {
            $('#error-repassword').text('');
        }

        if (isValid) {
            console.log('submit')
            if(token !=null){
                const salt = dcodeIO.bcrypt.genSaltSync(10);
                const hashedPassword = dcodeIO.bcrypt.hashSync(password, salt);
                console.log(hashedPassword)
                    $.ajax({
                        type: 'GET',
                        url: '/auth/handle-reset-password',
                        data: {
                            newPassword: hashedPassword,
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
        }
    });
});
