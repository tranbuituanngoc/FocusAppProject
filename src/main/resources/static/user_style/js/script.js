window.addEventListener('scroll', function () {
    var header = document.querySelector('.header');
    if (window.pageYOffset > 100) {
        header.classList.add('header-sticky');
    } else {
        header.classList.remove('header-sticky');
    }
});

// Toggle menu
$(document).ready(function () {
    // Lấy URL hiện tại
    var currentUrl = window.location.href;

// Kiểm tra các đường link của thẻ a trong .header-menu a
    var isHeaderMenuActive = false;
    $('.header-menu a').each(function () {
        if (this.href === currentUrl) {
            $('.header-menu').removeClass('header-menu-active');
            $('.header-title').removeClass('title-active');

            $(this).parent('.header-menu').addClass('header-menu-active');
            $(this).parent('.header-menu').find('.header-title').addClass('title-active');
            isHeaderMenuActive = true;
        }
    });

// Nếu không tìm thấy khớp với URL hiện tại trong .header-menu a, kiểm tra trong .child-menu a
    if (!isHeaderMenuActive) {
        $('.child-menu a').each(function () {
            if (this.href === currentUrl) {
                // Xóa class 'header-menu-active' và 'title-active' khỏi tất cả các phần tử menu
                $('.header-menu').removeClass('header-menu-active');
                $('.header-title').removeClass('title-active');

                // Thêm class 'header-menu-active' và 'title-active' vào phần tử cha của dropdown
                $(this).closest(".study-dropdown, .user-dropdown").prev(".header-menu").addClass("header-menu-active");
                $(this).closest(".study-dropdown, .user-dropdown").prev(".header-menu").find('.header-title').addClass('title-active');
            }
        });
    }
});

// Link with DKMH
$(".button-link-dkmh").click(function () {
    const proxyUrl = 'http://127.0.0.1:5300/';
    Swal.fire({
        title: 'Liên kết với trang ĐKMH',
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
                    //get semester
                    getSemester(result.value.accessToken, proxyUrl);
                    $.ajax({
                        url: 'api/ctdt/isExist',
                        type: 'GET',
                        data: {
                            year: parseInt('20' + mssv.substring(0, 2)),
                            departmentID: user.department.id,
                            userId: user.id
                        },
                        success: async function (response) {
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
                                            "loai_chuong_trinh_dao_tao": 1
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
                                        $.ajax({
                                            url: '/api/ctdt/create',
                                            type: 'POST',
                                            data: JSON.stringify(trainingProgramDTO),
                                            contentType: 'application/json',
                                            success: async function (message) {
                                                await getScore(access_token, proxyUrl);
                                                $.ajax({
                                                    url: 'api/hocki/current',
                                                    type: 'GET',
                                                    contentType: 'application/json',
                                                    success: function (response) {
                                                        getSchedule(access_token, proxyUrl, response);
                                                        getTestSchedule(access_token, proxyUrl, response);
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
                                    },
                                    error: function (error) {
                                        console.error(error);
                                    }
                                });
                            } else {
                                //get score
                                await getScore(result.value.accessToken, proxyUrl);
                                $.ajax({
                                    url: 'api/hocki/current',
                                    type: 'GET',
                                    contentType: 'application/json',
                                    success: function (response) {
                                        getSchedule(result.value.accessToken, proxyUrl, response);
                                        getTestSchedule(result.value.accessToken, proxyUrl, response);
                                    },
                                    error: function (error) {
                                        console.error(error);
                                    }
                                });
                            }
                        },
                        error: function (error) {
                            console.error(error);
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

// Reload data
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
    const now = new Date();
    const nowGMT = new Date(now.getTime() + now.getTimezoneOffset() * 60000);
    const expires = new Date(data.expires);
    let access_token;
    if (expires > nowGMT) {
        access_token = data.access_token;
        getSemester(access_token, proxyUrl);
        await getScore(access_token, proxyUrl);
        $.ajax({
            url: 'api/hocki/current',
            type: 'GET',
            contentType: 'application/json',
            success: function (response) {
                getSchedule(access_token, proxyUrl, response);
                getTestSchedule(access_token, proxyUrl, response);
                location.reload();
            },
            error: function (error) {
                console.error(error);
            }
        });
    } else {
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
                $.ajax({
                    url: '/api/dkmh/link',
                    type: 'POST',
                    data: JSON.stringify(linkData),
                    contentType: 'application/json',
                    success: async function (message) {
                        access_token = result.value.accessToken;
                        getSemester(access_token, proxyUrl);
                        await getScore(access_token, proxyUrl);
                        $.ajax({
                            url: 'api/hocki/current',
                            type: 'GET',
                            contentType: 'application/json',
                            success: function (response) {
                                getSchedule(access_token, proxyUrl, response);
                                getTestSchedule(access_token, proxyUrl, response);
                                location.reload();
                            },
                            error: function (error) {
                                console.error(error);
                            }
                        });
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
                    title: "Cập nhật dữ liệu thất bại",
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
// Change password
$(".change-password").click(function () {
    Swal.fire({
        title: "Đổi Mật Khẩu",
        html:
            '<input id="old-password" class="swal2-input  input-alert-class" placeholder="Mật khẩu cũ" type="password" required>' +
            '<input id="new-password" class="swal2-input  input-alert-class" placeholder="Mật khẩu mới" type="password" required>' +
            '<input id="confirm-password" class="swal2-input  input-alert-class" placeholder="Xác nhận mật khẩu mới" type="password" required>',
        focusConfirm: false,
        showCancelButton: true,
        reverseButtons: true,
        confirmButtonText: 'Đổi mật khẩu',
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
            const passwordInput = Swal.getInput();
            passwordInput.addEventListener('input', () => {
                Swal.resetValidationMessage();
            });
        },
        preConfirm: () => {
            const oldPassword = document.getElementById('old-password').value;
            const newPassword = document.getElementById('new-password').value;
            const confirmPassword = document.getElementById('confirm-password').value;
            const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z\d]).{8,}$/gm;

            if (!oldPassword || !newPassword || !confirmPassword) {
                return Swal.showValidationMessage('Vui lòng nhập đầy đủ thông tin!');
            }
            if(oldPassword === newPassword){
                return Swal.showValidationMessage('Mật khẩu mới không được trùng với mật khẩu cũ!');
            }

            if (newPassword !== confirmPassword) {
                return Swal.showValidationMessage('Mật khẩu mới không khớp!');
            }
            if (!passwordRegex.test(newPassword)) {
                return Swal.showValidationMessage('Mật khẩu mới phải chứa ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số!');
            }
            return {
                oldPassword: oldPassword,
                newPassword: newPassword,
            };
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        $.ajax({
            url: '/api/nguoi-dung/changePassword/' + user.id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                old_password: result.value.oldPassword,
                new_password: result.value.newPassword
            }),
            success: function (response) {
                if(response){
                    Swal.fire({
                        icon: "success",
                        title: "Thành công",
                        text: "Đổi mật khẩu thành công!",
                        customClass: {
                            confirmButton: 'confirm-button-class',
                            cancelButton: 'cancel-button-class',
                            title: 'title-alert-class',
                        },
                        confirmButtonText: 'OK!'
                    });
                }else{
                    Swal.fire({
                        icon: "error",
                        title: "Thất bại",
                        text: "Mật khẩu cũ không đúng!",
                        customClass: {
                            confirmButton: 'confirm-button-class',
                            cancelButton: 'cancel-button-class',
                            title: 'title-alert-class',
                        },
                    });
                }
            },
            error: function (response) {
                Swal.fire({
                    icon: "error",
                    title: "Thất bại",
                    text: response.responseText,
                    customClass: {
                        confirmButton: 'confirm-button-class',
                        cancelButton: 'cancel-button-class',
                        title: 'title-alert-class',
                    },
                });
            }
        });
    })
});

function getSemester(accessToken, proxyUrl) {
    //get semesters
    const targetUrl = 'https://dkmh.hcmuaf.edu.vn/api/sch/w-locdshockytkbuser';
    const finalUrl = proxyUrl + targetUrl;
    $.ajax({
        url: finalUrl,
        type: 'POST',
        contentType: 'application/json',
        headers: {
            'Authorization': 'Bearer ' + accessToken
        },
        data: JSON.stringify({
            "filter": {"is_tieng_anh": null},
            "additional": {
                "paging": {
                    "limit": 100,
                    "page": 1
                },
                "ordering": [{
                    "name": "hoc_ky",
                    "order_type": 1
                }
                ]
            },
        }),
        success: function (response) {
            const semesters = convertToSemesterDTO(response);
            $.ajax({
                url: '/api/hocki/create',
                type: 'POST',
                data: JSON.stringify(semesters),
                contentType: 'application/json',
                success: function (message) {
                    Toastify({
                        text: message,
                        duration: 3000,
                        close: true,
                        gravity: "bottom", // `top` or `bottom`
                        position: "right", // `left`, `center` or `right`
                        stopOnFocus: true, // Prevents dismissing of toast on hover
                        className: "my-toast",
                        style: {
                            background: '#59D5E0',
                        },
                        onClick: function () {
                        } // Callback after click
                    }).showToast();
                },
                error: function (error) {
                    Toastify({
                        text: error,
                        duration: 3000,
                        close: true,
                        gravity: "bottom", // `top` or `bottom`
                        position: "right", // `left`, `center` or `right`
                        stopOnFocus: true, // Prevents dismissing of toast on hover
                        className: "my-toast",
                        style: {
                            background: '#F4538A',
                        },
                        onClick: function () {
                        } // Callback after click
                    }).showToast();
                    console.error(error);
                }
            });
        },
        error: function (error) {
            console.error(error);
        }
    });
}

function getSchedule(accessToken, proxyUrl, semesterId) {
    //get schedules
    const targetUrl = 'https://dkmh.hcmuaf.edu.vn/api/sch/w-locdstkbtuanusertheohocky';
    const finalUrl = proxyUrl + targetUrl;
    $.ajax({
        url: finalUrl,
        type: 'POST',
        contentType: 'application/json',
        headers: {
            'Authorization': 'Bearer ' + accessToken
        },
        data: JSON.stringify({
            "filter": {
                "hoc_ky": semesterId,
                "ten_hoc_ky": ""
            },
            "additional": {
                "paging": {
                    "limit": 100,
                    "page": 1
                },
                "ordering": [{
                    "name": null,
                    "order_type": null
                }]
            }
        }),
        success: function (response) {
            const userScheduleDTO = convertToUserScheduleDTO(response, user.id, semesterId);
            $.ajax({
                url: '/api/tkb/create',
                type: 'POST',
                data: JSON.stringify(userScheduleDTO),
                contentType: 'application/json',
                success: function (message) {
                    Toastify({
                        text: message,
                        duration: 3000,
                        close: true,
                        gravity: "bottom", // `top` or `bottom`
                        position: "right", // `left`, `center` or `right`
                        stopOnFocus: true, // Prevents dismissing of toast on hover
                        className: "my-toast",
                        style: {
                            background: '#59D5E0',
                        },
                        onClick: function () {
                        } // Callback after click
                    }).showToast();
                },
                error: function (error) {
                    Toastify({
                        text: error,
                        duration: 3000,
                        close: true,
                        gravity: "bottom", // `top` or `bottom`
                        position: "right", // `left`, `center` or `right`
                        stopOnFocus: true, // Prevents dismissing of toast on hover
                        className: "my-toast",
                        style: {
                            background: '#F4538A',
                        },
                        onClick: function () {
                        } // Callback after click
                    }).showToast();
                    console.error(error);
                }
            });
        },
        error: function (error) {
            console.error(error);
        }
    });
}

function getTestSchedule(accessToken, proxyUrl, semesterId) {
    //get test schedules
    const targetUrl = 'https://dkmh.hcmuaf.edu.vn/api/epm/w-locdslichthisvtheohocky';
    const finalUrl = proxyUrl + targetUrl;
    $.ajax({
        url: finalUrl,
        type: 'POST',
        contentType: 'application/json',
        headers: {
            'Authorization': 'Bearer ' + accessToken
        },
        data: JSON.stringify({
            "filter": {
                "hoc_ky": semesterId
            },
            "additional": {
                "paging": {
                    "limit": 100,
                    "page": 1
                },
                "ordering": [{
                    "name": null, "order_type": null
                }
                ]
            }
        }),
        success: function (response) {
            if (response.data.ds_lich_thi.length !== 0) {
                const userTestScheduleDTO = convertToUserTestScheduleDTO(response, user.id, semesterId);
                $.ajax({
                    url: '/api/lich-thi/create',
                    type: 'POST',
                    data: JSON.stringify(userTestScheduleDTO),
                    contentType: 'application/json',
                    success: function (message) {
                        Toastify({
                            text: message,
                            duration: 3000,
                            close: true,
                            gravity: "bottom", // `top` or `bottom`
                            position: "right", // `left`, `center` or `right`
                            stopOnFocus: true, // Prevents dismissing of toast on hover
                            className: "my-toast",
                            style: {
                                background: '#59D5E0',
                            },
                            onClick: function () {
                            } // Callback after click
                        }).showToast();
                    },
                    error: function (error) {
                        Toastify({
                            text: error,
                            duration: 3000,
                            close: true,
                            gravity: "bottom", // `top` or `bottom`
                            position: "right", // `left`, `center` or `right`
                            stopOnFocus: true, // Prevents dismissing of toast on hover
                            className: "my-toast",
                            style: {
                                background: '#F4538A',
                            },
                            onClick: function () {
                            } // Callback after click
                        }).showToast();
                        console.error(error);
                    }
                });
            } else {
                console.log("No test schedule");
            }
        },
        error: function (error) {
            console.error(error);
        }
    });
}

function getScore(accessToken, proxyUrl) {
    return new Promise((resolve, reject) => {
        //get scores
        const targetUrl = 'https://dkmh.hcmuaf.edu.vn/api/srm/w-locdsdiemsinhvien?hien_thi_mon_theo_hkdk=false';
        const finalUrl = proxyUrl + targetUrl;
        $.ajax({
            url: finalUrl,
            type: 'POST',
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + accessToken
            },
            success: function (response) {
                const userSemesterDTO = convertToUserSemesterDTO(response, user.id);
                $.ajax({
                    url: '/api/diem/create',
                    type: 'POST',
                    data: JSON.stringify(userSemesterDTO),
                    contentType: 'application/json',
                    success: function (message) {
                        Toastify({
                            text: message,
                            duration: 3000,
                            close: true,
                            gravity: "bottom", // `top` or `bottom`
                            position: "right", // `left`, `center` or `right`
                            stopOnFocus: true, // Prevents dismissing of toast on hover
                            className: "my-toast",
                            style: {
                                background: '#59D5E0',
                            },
                            onClick: function () {
                            } // Callback after click
                        }).showToast();
                        resolve(message);
                    },
                    error: function (error) {
                        Toastify({
                            text: error,
                            duration: 3000,
                            close: true,
                            gravity: "bottom", // `top` or `bottom`
                            position: "right", // `left`, `center` or `right`
                            stopOnFocus: true, // Prevents dismissing of toast on hover
                            className: "my-toast",
                            style: {
                                background: '#F4538A',
                            },
                            onClick: function () {
                            } // Callback after click
                        }).showToast();
                        console.error(error);
                        reject(error);
                    }
                });
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}


function convertToTrainingProgramDTO(response, mssv) {
    // create a DepartmentDTO object from the API data
    const item = response.data.ds_nganh_sinh_vien[0];

    const department = {
        department_id: item.ma_nganh === 'DT22' ? 52480201 : item.ma_nganh,
        department_name: item.ten_nganh
    };

    // create a SemesterDTO array from the API data
    const semesters = response.data.ds_CTDT_hocky.map(hocKy => {
        // create a CourseDTO array from the API data
        const courses = hocKy.ds_CTDT_mon_hoc.map(monHoc => {
            return {
                course_id: monHoc.ma_mon === 'TH' ? 1 : monHoc.ma_mon === 'NN' ? 2 : monHoc.ma_mon,
                course_name: monHoc.ten_mon,
                is_mandatory: monHoc.mon_bat_buoc === 'x',
                theory_hours: monHoc.ly_thuyet ? parseInt(monHoc.ly_thuyet) : null,
                practice_hours: monHoc.thuc_hanh ? parseInt(monHoc.thuc_hanh) : null,
                credits: parseInt(monHoc.so_tin_chi) ? parseInt(monHoc.so_tin_chi) : null,
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
        list_semesters: semesters,
        user_id: user.id
    };

    return trainingProgramDTO;
}

function convertToUserTestScheduleDTO(response, userid, semesterid) {
    const testSchedules = response.data.ds_lich_thi.map(lichThi => {
        return {
            course_id: lichThi.ma_mon,
            test_room: lichThi.ma_phong,
            test_date: lichThi.ngay_thi,
            test_time: lichThi.gio_bat_dau,
            start_slot: lichThi.tiet_bat_dau
        };
    });
    const userTestScheduleDTO = {
        user_id: userid,
        semester_id: semesterid,
        list_test_schedules: testSchedules
    };
    return userTestScheduleDTO;
}

function convertToSemesterDTO(response) {
    const semesters = response.data.ds_hoc_ky.map(hocKy => {
        return {
            semester_id: hocKy.hoc_ky,
            semester_name: hocKy.ten_hoc_ky,
            start_date: hocKy.ngay_bat_dau_hk,
            end_date: hocKy.ngay_ket_thuc_hk
        };
    });
    return semesters;
}

function convertToUserScheduleDTO(response, userid, semesterid) {
    const weeks = response.data.ds_tuan_tkb.map(tuan => {
        const course_schedules = tuan.ds_thoi_khoa_bieu.map(tkb => {
            return {
                course_id: tkb.ma_mon,
                is_practice: tkb.ma_to_th !== "",
                study_slot: parseInt(tkb.tiet_bat_dau),
                date_num_type: parseInt(tkb.thu_kieu_so),
                course_room: tkb.ma_phong,
                num_of_lession: parseInt(tkb.so_tiet)
            };
        });

        return {
            semester_week: tuan.tuan_hoc_ky,
            start_date: tuan.ngay_bat_dau,
            end_date: tuan.ngay_ket_thuc,
            course_schedules
        };
    });
    const userScheduleDTO = {
        user_id: userid,
        semester_id: semesterid,
        list_weeks: weeks
    };
    return userScheduleDTO;
}

function convertToUserSemesterDTO(response, userid) {
    const semesters = response.data.ds_diem_hocky.map(hocKy => {
        const scores = hocKy.ds_diem_mon_hoc.map(monHoc => {
            return {
                course_id: monHoc.ma_mon,
                component_score: parseFloat(monHoc.diem_giua_ky),
                score: parseFloat(monHoc.diem_thi),
                final_score_10: parseFloat(monHoc.diem_tk),
                final_score_4: parseFloat(monHoc.diem_tk_so),
                final_score_char: monHoc.diem_tk_chu,
                result: monHoc.ket_qua === 1
            };
        });

        return {
            semester_id: hocKy.hoc_ky,
            gpa_4: parseFloat(hocKy.dtb_hk_he4),
            cumulative_gpa_4: parseFloat(hocKy.dtb_tich_luy_he_4),
            gpa_10: parseFloat(hocKy.dtb_hk_he10),
            cumulative_gpa_10: parseFloat(hocKy.dtb_tich_luy_he_10),
            cumulative_credit: parseInt(hocKy.so_tin_chi_dat_tich_luy),
            credit_hours: parseInt(hocKy.so_tin_chi_dat_hk),
            list_scores: scores
        };
    });
    const userSemesterDTO = {
        user_id: userid,
        list_semesters: semesters
    };
    return userSemesterDTO;
}
