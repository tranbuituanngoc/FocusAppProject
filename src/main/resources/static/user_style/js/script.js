$(".button-link-dkmh").click(function () {
    const proxyUrl = 'http://127.0.0.1:5300/';
    Swal.fire({
        title: 'Submit your information',
        html:
            '<input id="mssv-input" class="swal2-input input-alert-class" placeholder="MSSV" type="number" require>' +
            '<input id="password-input" class="swal2-input input-alert-class" placeholder="Password" type="password" require>',
        focusConfirm: false,
        showCancelButton: true,
        reverseButtons: true,
        confirmButtonText: 'Liên kết',
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
        onOpen: () => {
            const mssvInput = Swal.getInput();
            mssvInput.addEventListener('input', () => {
                Swal.resetValidationMessage();
            });
        },
        preConfirm: async () => {
            const mssv = document.getElementById('mssv-input').value;
            const password = document.getElementById('password-input').value;

            if (!/^[1-9][0-9]{7}$/.test(mssv)) {
                Swal.showValidationMessage('MSSV phải là một số có 8 chữ số và không bắt đầu bằng số 0');
            } else {
                const targetUrl = 'https://dkmh.hcmuaf.edu.vn/api/auth/login';
                const finalUrl = proxyUrl + targetUrl;
                const data = new URLSearchParams();
                data.append('username', mssv);
                data.append('password', password);
                data.append('grant_type', 'password');

                try {
                    const response = await fetch(finalUrl, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: data
                    });

                    const responseData = await response.json();

                    if (responseData.code === 403) {
                        return Swal.showValidationMessage(responseData.message);
                    }

                    if (!response.ok) {
                        let message;
                        try {
                            message = JSON.stringify(responseData);
                        } catch (e) {
                            message = await response.text();
                        }
                        return Swal.showValidationMessage(message);
                    }
                    const {userName, access_token, refresh_token, token_type} = responseData;
                    return {
                        mssv: userName,
                        accessToken: access_token,
                        refreshToken: refresh_token,
                        tokenType: token_type
                    };
                } catch (error) {
                    Swal.showValidationMessage(`Request failed: ${error.message}`);
                }
            }
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            const mssv = document.getElementById('mssv-input').value;
            const linkData = {
                mssv: mssv,
                access_token: result.value.accessToken,
                refresh_token: result.value.refreshToken,
                token_type: result.value.tokenType,
                user_id: user.id
            };
            $.ajax({
                url: '/api/dkmh/link',
                type: 'POST',
                data: JSON.stringify(linkData),
                contentType: 'application/json',
                success: function (message) {
                    $.ajax({
                        url: 'api/ctdt/isExist',
                        type: 'GET',
                        data: {
                            year: parseInt('20' + mssv.substring(0, 2)),
                            departmentID: user.department.id
                        },
                        success: function (response) {
                            if (!response) {
                                //get training program
                                const access_token = result.value.accessToken;
                                const targetUrl = 'https://dkmh.hcmuaf.edu.vn/api/sch/w-locdsctdtsinhvien';
                                const finalUrl = proxyUrl + targetUrl;
                                $.ajax({
                                    url: finalUrl,
                                    type: 'POST',
                                    contentType: 'application/json',
                                    headers: {
                                        'Authorization': 'Bearer ' + access_token
                                    },
                                    data: JSON.stringify({
                                        "filter": {
                                            "loai_chuong_trinh_dao_tao": 2
                                        },
                                        "additional": {
                                            "paging": {
                                                "limit": 500,
                                                "page": 1
                                            },
                                            "ordering": [
                                                {
                                                    "name": null,
                                                    "order_type": null
                                                }
                                            ]
                                        }
                                    }),
                                    success: function (response) {
                                        const trainingProgramDTO = convertToTrainingProgramDTO(response, mssv);
                                        console.log(trainingProgramDTO);
                                        $.ajax({
                                            url: '/api/ctdt/create',
                                            type: 'POST',
                                            data: JSON.stringify(trainingProgramDTO),
                                            contentType: 'application/json',
                                            success: function (message) {
                                                console.log(message);
                                            },
                                            error: function (error) {
                                                console.error(error);
                                            }
                                        });
                                    },
                                    error: function (error) {
                                        console.error(error);
                                    }
                                });
                            }
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    })
                    Swal.fire({
                        icon: "success",
                        title: message,
                        customClass: {
                            confirmButton: 'confirm-button-class',
                            cancelButton: 'cancel-button-class',
                            title: 'title-alert-class',
                        },
                        confirmButtonText: 'OK!'
                    });
                },
                error: function (error) {
                    Swal.fire({
                        icon: "error",
                        title: "Liên kết thất bại",
                        text: error,
                        customClass: {
                            confirmButton: 'confirm-button-class',
                            cancelButton: 'cancel-button-class',
                            title: 'title-alert-class',
                        },
                    });
                }
            });
        } else {
            Swal.fire({
                icon: "error",
                title: "Liên kết thất bại",
                text: "Lỗi, vui lòng thử lại!",
                customClass: {
                    confirmButton: 'confirm-button-class',
                    cancelButton: 'cancel-button-class',
                    title: 'title-alert-class',
                },
            });
        }
    });
});

$(".button-reload-data").click(async function () {
    const proxyUrl = 'http://127.0.0.1:5300/';
    const response = await fetch('/api/dkmh/get-expire/' + user.id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const data = await response.json();
    const mssv = data.mssv;
    const expiresGMT = new Date(data.expires + 'Z'); // append 'Z' to indicate it's in GMT
    const now = new Date(); // get current date and time
    const nowGMT = new Date(now.getTime() + now.getTimezoneOffset() * 60000);
    console.log(mssv);
    console.log(expiresGMT);
    console.log(nowGMT);
    if (expiresGMT > nowGMT) {
        console.log("Not expired");
    } else {
        console.log("Expired");
        Swal.fire({
            title: 'Submit your information',
            html:
                '<input id="password-input" class="swal2-input input-alert-class" placeholder="Password" type="password" require>',
            focusConfirm: false,
            showCancelButton: true,
            reverseButtons: true,
            confirmButtonText: 'Cập nhật',
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
            preConfirm: async () => {
                const password = document.getElementById('password-input').value;

                const targetUrl = 'https://dkmh.hcmuaf.edu.vn/api/auth/login';
                const finalUrl = proxyUrl + targetUrl;
                const data = new URLSearchParams();
                data.append('username', mssv);
                data.append('password', password);
                data.append('grant_type', 'password');

                try {
                    const response = await fetch(finalUrl, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: data
                    });

                    const responseData = await response.json();

                    if (responseData.code === 403) {
                        return Swal.showValidationMessage(responseData.message);
                    }

                    if (!response.ok) {
                        let message;
                        try {
                            message = JSON.stringify(responseData);
                        } catch (e) {
                            message = await response.text();
                        }
                        return Swal.showValidationMessage(message);
                    }
                    const {userName, access_token, refresh_token, token_type} = responseData;
                    return {
                        mssv: userName,
                        accessToken: access_token,
                        refreshToken: refresh_token,
                        tokenType: token_type
                    };
                } catch (error) {
                    Swal.showValidationMessage(`Request failed: ${error.message}`);
                }

            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                const linkData = {
                    mssv: mssv,
                    access_token: result.value.accessToken,
                    refresh_token: result.value.refreshToken,
                    token_type: result.value.tokenType,
                    user_id: user.id
                };
                const access_token = result.value.accessToken;
                $.ajax({
                    url: '/api/dkmh/link',
                    type: 'POST',
                    data: JSON.stringify(linkData),
                    contentType: 'application/json',
                    success: function (message) {


                        Swal.fire({
                            icon: "success",
                            title: message,
                            customClass: {
                                confirmButton: 'confirm-button-class',
                                cancelButton: 'cancel-button-class',
                                title: 'title-alert-class',
                            },
                            focusConfirm: false,
                            confirmButtonText: 'OK!'
                        });
                    },
                    error: function (error) {
                        Swal.fire({
                            icon: "error",
                            title: "Liên kết thất bại",
                            text: error,
                            customClass: {
                                confirmButton: 'confirm-button-class',
                                cancelButton: 'cancel-button-class',
                                title: 'title-alert-class',
                            },
                        });
                    }
                });
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Liên kết thất bại",
                    text: "Lỗi, vui lòng thử lại!",
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

function convertToTrainingProgramDTO(response, mssv) {
    // create a DepartmentDTO object from the API data
    const item = response.data.ds_nganh_sinh_vien[0];

    const department = {
        department_id: item.ma_nganh,
        department_name: item.ten_nganh
    };

    // create a SemesterDTO array from the API data
    const semesters = response.data.ds_CTDT_hocky.map(hocKy => {
        // create a CourseDTO array from the API data
        const courses = hocKy.ds_CTDT_mon_hoc.map(monHoc => {
            return {
                course_id: monHoc.ma_mon,
                course_name: monHoc.ten_mon,
                is_mandatory: monHoc.mon_bat_buoc === 'x',
                theory_hours: monHoc.ly_thuyet ? parseInt(monHoc.ly_thuyet) : null,
                practice_hours: monHoc.thuc_hanh ? parseInt(monHoc.thuc_hanh) : null,
                credits: parseInt(monHoc.so_tin_chi)
            };
        });

        return {
            semester_id: hocKy.hoc_ky,
            semester_name: hocKy.ten_hoc_ky,
            list_courses: courses
        };
    });

    // create a TrainingProgramDTO object from the DepartmentDTO and SemesterDTO array
    const year = parseInt('20' + mssv.substring(0, 2))
    const trainingProgramDTO = {
        year: year,
        department: department,
        list_semesters: semesters
    };

    return trainingProgramDTO;
}
