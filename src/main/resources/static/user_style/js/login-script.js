$(document).ready(function () {
    if (info != null) {
        Swal.fire({
            icon: "warning",
            title: "Xác thực tài khoản không thành công!",
            text: "Thời gian xác thực đã hết hạn. Vui lòng xác thực lại.",
            focusConfirm: false,
            showCancelButton: true,
            reverseButtons: true,
            confirmButtonText: 'Xác thực lại',
            cancelButtonText: 'Hủy',
            inputAttributes: {
                autocapitalize: "off"
            },
            customClass: {
                confirmButton: 'confirm-button-class',
                cancelButton: 'cancel-button-class',
                title: 'title-alert-class',
                validationMessage: 'my-validation-message',
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
               $.ajax({
                   url:"/auth/re-verify",
                   method:"GET",
                   contentType: "application/json",
                   data: {
                       userId: info
                   },
                   success: function (data) {
                       const type= data.left;
                       if (type === "success") {
                           Swal.fire({
                               icon: "success",
                               title: "Xác thực tài khoản thành công!",
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
                               title: "Xác thực tài khoản thất bại!",
                               text: data.right,
                               customClass: {
                                   confirmButton: 'confirm-button-class',
                                   cancelButton: 'cancel-button-class',
                                   title: 'title-alert-class',
                               },
                           });
                       }
                   },
                     error: function (error) {
                          console.error(error)
                     }
               })
            }
        });
    } else {
        if (error != null) {
            console.log(error)
            Swal.fire({
                icon: "error",
                title: "Đăng nhập thất bại!",
                text: error,
                customClass: {
                    confirmButton: 'confirm-button-class',
                    cancelButton: 'cancel-button-class',
                    title: 'title-alert-class',
                },
            });
        }
        if (success != null) {
            console.log(success)
            Swal.fire({
                icon: "success",
                title: "Thành công!",
                text: success,
                customClass: {
                    confirmButton: 'confirm-button-class',
                    cancelButton: 'cancel-button-class',
                    title: 'title-alert-class',
                },
            });
        }
    }
});