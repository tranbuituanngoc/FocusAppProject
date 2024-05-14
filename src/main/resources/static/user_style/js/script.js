$(".button-link-dkmh").click(function () {
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
                const proxyUrl = 'http://127.0.0.1:5300/';
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