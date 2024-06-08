$(document).ready(function () {
    $.ajax({
        url: '/api/nguoi-dung/isUpdate/' + user.id,
        type: 'GET',
        success: function (data) {
            console.log(data);
            if (!data) {
                const swalWithBootstrapButtons = Swal.mixin({
                    customClass: {
                        confirmButton: 'confirm-button-class',
                        cancelButton: 'cancel-button-class',
                        title: 'title-alert-class',
                        validationMessage: 'my-validation-message',
                    },
                    reverseButtons: true,
                    buttonsStyling: true,
                })
                let departmentValue = null;
                let desiredScoreValue = null;
                const step1 = () => {
                    $.ajax({
                        url: '/api/nguoi-dung/getDepartment',
                        type: 'GET',
                        success: function (data) {
                            let departments = {};
                            data.forEach(department => {
                                departments[department.department_id] = department.department_name;
                            });

                            swalWithBootstrapButtons.fire({
                                title: 'Chọn khoa của bạn',
                                input: 'select',
                                confirmButtonText: 'Tiếp theo',
                                inputOptions: departments,
                                inputValue: departmentValue,
                                inputPlaceholder: 'Chọn khoa',
                                showCancelButton: false,
                                allowOutsideClick: false,
                                inputValidator: (value) => {
                                    if (!value) {
                                        return 'Bạn cần phải chọn khoa của mình!'
                                    }
                                }
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    departmentValue = result.value;
                                    step2(result)
                                }
                            })
                            const step2 = (result) => {
                                if (result.isConfirmed) {
                                    swalWithBootstrapButtons.fire({
                                        title: 'Nhập điểm số mong muốn của bạn',
                                        confirmButtonText: 'Xác nhận',
                                        cancelButtonText: 'Quay lại',
                                        html: '<input id="desire-input" class="swal2-input input-number-form" placeholder="Điểm GPA hệ 4 mong muốn" type="number" required>',
                                        showCancelButton: true,
                                        allowOutsideClick: false,
                                        preConfirm: () => {
                                            const value = document.getElementById('desire-input').value;
                                            if (!value) {
                                                Swal.showValidationMessage('Bạn cần phải nhập điểm số mong muốn!');
                                            } else if (value > 4) {
                                                Swal.showValidationMessage('Điểm của bạn không được lớn hơn 4!');
                                            }
                                            return value;
                                        }
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            desiredScoreValue = parseFloat(result.value).toFixed(2);
                                            if (departmentValue !== null && desiredScoreValue !== null) {
                                                $.ajax({
                                                    url: '/api/nguoi-dung/updateInfo',
                                                    type: 'PUT',
                                                    contentType: 'application/json',
                                                    data: JSON.stringify({
                                                        user_id: user.id,
                                                        department_id: departmentValue,
                                                        desired_score: desiredScoreValue
                                                    }),
                                                    success: function (data) {
                                                        console.log(data);
                                                        if (data) {
                                                            swalWithBootstrapButtons.fire({
                                                                title: 'Cập nhật thông tin thành công!',
                                                                icon: 'success',
                                                                confirmButtonText: 'OK',
                                                                allowOutsideClick: false,
                                                            }).then((result) => {
                                                                if (result.isConfirmed) {
                                                                    location.reload();
                                                                }
                                                            })
                                                        }
                                                    },
                                                    error: function (error) {
                                                        console.error(error);
                                                    }
                                                });
                                            }
                                        } else if (result.dismiss === Swal.DismissReason.cancel) {
                                            step1({isConfirmed: true, value: departmentValue})
                                        }
                                    })
                                }
                            }
                        },
                        error: function (error) {
                            console.error(error);
                        }
                    });
                };
                step1();
            }
        },
        error: function (error) {
            console.error(error);
        }
    });
});