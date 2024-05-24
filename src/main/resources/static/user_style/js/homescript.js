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
                let step1Value = null;
                let step2Value = null;
                let step3Value = null;
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
                                inputValue: step1Value,
                                inputPlaceholder: 'Chọn khoa',
                                showCancelButton: false,
                                allowOutsideClick: false,
                                inputValidator: (value) => {
                                    if (!value) {
                                        // Swal.showValidationMessage('Bạn cần phải chọn một giá trị!');
                                        return 'Bạn cần phải chọn một giá trị!'
                                    }
                                }
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    step1Value = result.value;
                                    step2(result);
                                }
                            })
                            const step2 = (result) => {
                                if (result.isConfirmed) {
                                    $.ajax({
                                        url: '/api/nguoi-dung/getMajor/' + result.value,
                                        type: 'GET',
                                        success: function (data) {
                                            let majors = {};
                                            data.forEach(major => {
                                                majors[major.major_id] = major.major_name;
                                            });

                                            swalWithBootstrapButtons.fire({
                                                title: 'Chọn chuyên ngành của bạn',
                                                input: 'select',
                                                confirmButtonText: 'Tiếp theo',
                                                inputOptions: majors,
                                                inputValue: step2Value,
                                                inputPlaceholder: 'Chọn chuyên ngành',
                                                showCancelButton: true,
                                                allowOutsideClick: false,
                                                inputValidator: (value) => {
                                                    if (!value) {
                                                        return 'Bạn cần phải chọn một giá trị!'
                                                    }
                                                }
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    step2Value = result.value;
                                                    step3(result)
                                                } else if (result.dismiss === Swal.DismissReason.cancel) {
                                                    step1()
                                                }
                                            })
                                            const step3 = (result) => {
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
                                                            step3Value = parseFloat(result.value).toFixed(2);
                                                            if (step1Value !== null && step2Value !== null && step3Value !== null) {
                                                                console.log("step1: " + step1Value)
                                                                console.log("step2: " + step2Value)
                                                                console.log("step3: " + step3Value)
                                                                $.ajax({
                                                                    url: '/api/nguoi-dung/updateInfo',
                                                                    type: 'PUT',
                                                                    contentType: 'application/json',
                                                                    data: JSON.stringify({
                                                                        user_id: user.id,
                                                                        department_id: step1Value,
                                                                        major_id: step2Value,
                                                                        desired_score: step3Value
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
                                                            step2({isConfirmed: true, value: step2Value})
                                                        }
                                                    })
                                                }
                                            }
                                        },
                                    });
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