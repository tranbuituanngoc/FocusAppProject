$(document).ready(function () {
    $('form').on('submit', function (e) {
        e.preventDefault();

        const email = $('#email').val();

        const emailRegex = /^[1-9][0-9]{7}@st\.hcmuaf\.edu\.vn$/;

        let isValid = true;

        if (!emailRegex.test(email)) {
            $('#error-email').text('Email không hợp lệ');
            isValid = false;
            console.log('email failed')
        } else {
            $('#error-email').text('');
        }

        if (isValid) {
            $.ajax({
                url: '/auth/handle-forgot-password',
                type: 'GET',
                contentType: 'application/json',
                data: {email: email},

                success: function (data) {
                    console.log(data)
                    const type = data.left;
                    if (type === "success") {
                        Swal.fire({
                            icon: "success",
                            title: "Thành công!",
                            text: data.right,
                            customClass: {
                                confirmButton: 'confirm-button-class',
                                cancelButton: 'cancel-button-class',
                                title: 'title-alert-class',
                            },
                        });
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Thất bại!",
                            text: data.right,
                            customClass: {
                                confirmButton: 'confirm-button-class',
                                cancelButton: 'cancel-button-class',
                                title: 'title-alert-class',
                            },
                        });
                    }
                },

                error: function (xhr, errmsg, err) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Có lỗi xảy ra! Vui lòng thử lại sau!',
                        customClass: {
                            confirmButton: 'confirm-button-class',
                            cancelButton: 'cancel-button-class',
                            title: 'title-alert-class',
                        },
                    });
                }
            });
        }
    });
});
