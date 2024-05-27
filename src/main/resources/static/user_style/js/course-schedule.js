$(document).ready(function () {
    $.ajax({
        url: "/api/tkb/get-weeks",
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            const currentWeek = data.start_date + " ~ " + data.end_date;
            $("#current-week").text(currentWeek);
            $('#current-week').attr('data-value', data.week_id);
            data.list_weeks.forEach(week => {
                const childMenu = $(`<div class="child-menu" data-value="${week.week_id}">${week.start_date} ~ ${week.end_date}</div>`);
                if (week.week_id === data.week_id) {
                    childMenu.addClass('active');
                }
                $("#list-weeks").append(childMenu);
            });
            getSchedule(data.week_id);

            function getSchedule(weekId) {
                $('#week').empty();
                $.ajax({
                    url: "/api/tkb/get-schedule",
                    type: "GET",
                    contentType: "application/json",
                    data: {
                        weekId: weekId,
                        userId: user.id
                    },
                    success: function (data) {
                        var currentDate = new Date();
                        currentDate.setHours(0, 0, 0, 0);
                        var daysOfWeek = ["Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"];
                        var daysOfWeekObject = {};
                        let count = 2;
                        $.each(data.dates, function (index, date) {
                            var dataDate = new Date(date);
                            dataDate.setHours(0, 0, 0, 0);
                            var day = dataDate.getDate();
                            var dayOfWeek = daysOfWeek[index];

                            var todoDateUnit = $('<div/>', {
                                class: 'todo-date-unit'
                            });

                            var dayElement = $('<div/>', {
                                text: dayOfWeek
                            });

                            var dateElement = $('<div/>', {
                                text: day,
                                class: 'date'
                            });

                            var lineElement = $('<div/>', {
                                class: 'date-bottom-line date-bottom-line-normal'
                            });

                            if (dataDate.getTime() < currentDate.getTime()) {
                                // past
                                dayElement.addClass('day-disable');
                                dateElement.addClass('date-disable');
                                daysOfWeekObject[count] = false;
                            } else if (dataDate.getTime() > currentDate.getTime()) {
                                // future
                                dayElement.addClass('day');
                                dateElement.addClass('date-coming');
                                daysOfWeekObject[count] = true;
                            } else {
                                // current
                                dayElement.addClass('day');
                                dateElement.addClass('date-active');
                                lineElement.addClass('date-bottom-line-active');
                                lineElement.removeClass('date-bottom-line-normal');
                                daysOfWeekObject[count] = true;
                            }

                            todoDateUnit.append(dayElement, dateElement, lineElement);
                            $('#week').append(todoDateUnit);
                            count++;
                        });
                        data.list_course.forEach(course => {
                            let subjectContent;
                            console.log(course.num_of_lession)
                            if (course.num_of_lession === 3) {
                                const subjectId = "t" + course.date_num_type + "-slot" + course.study_slot;
                                const subject = $("#" + subjectId);
                                subject.removeClass('not-study');

                                subjectContent = course.course_name + "<br/>Phòng học: " + course.course_room;
                                subject.find('.subject-details').html(subjectContent);


                                if (!daysOfWeekObject[course.date_num_type]) {
                                    subject.addClass('subject-disable');
                                }
                                if (course.is_practice) {
                                    subject.addClass('practice');
                                } else {
                                    subject.addClass('theory');
                                }
                            } else if (course.num_of_lession === 6) {
                                const subjectId1 = "t" + course.date_num_type + "-slot" + course.study_slot;
                                const courseSlot = parseInt(course.study_slot) + 3;
                                const subjectId2 = "t" + course.date_num_type + "-slot" + courseSlot;
                                console.log(subjectId1)
                                console.log(subjectId2)
                                const subject1 = $("#" + subjectId1);
                                const subject2 = $("#" + subjectId2);
                                subject1.removeClass('not-study');
                                subject2.removeClass('not-study');

                                subjectContent = course.course_name + "<br/>Phòng học: " + course.course_room;
                                subject1.find('.subject-details').html(subjectContent);
                                subject2.find('.subject-details').html(subjectContent);

                                if (!daysOfWeekObject[course.date_num_type]) {
                                    subject1.addClass('subject-disable');
                                    subject2.addClass('subject-disable');
                                }
                                if (course.is_practice) {
                                    subject1.addClass('practice');
                                    subject2.addClass('practice');
                                } else {
                                    subject1.addClass('theory');
                                    subject2.addClass('theory');
                                }
                            }
                        });
                    },
                    error: function (data) {
                        console.error(data);
                    }
                })
            }

            $('.child-menu').click(function () {
                var text = $(this).text();
                var value = $(this).data('value');

                $('#current-week').text(text);
                $('#current-week').data('value', value);
                resetWeekSubject();
                getSchedule(value);
            });

            function resetWeekSubject() {
                $('#week-subject').empty();

                var daysOfWeek = {
                    "monday-subject": "thu-2",
                    "tuesday-subject": "thu-3",
                    "wednessday-subject": "thu-4",
                    "thursday-subject": "thu-5",
                    "friday-subject": "thu-6",
                    "saturday-subject": "thu-7",
                    "sunday-subject": "thu-8"
                };
                var slots = ["slot1", "slot4", "slot7", "slot10"];

                Object.entries(daysOfWeek).forEach(function ([key, value]) {
                    var dayElement = $('<div/>', {
                        class: key,
                        id: value
                    });

                    slots.forEach(function (slot) {
                        var slotElement = $('<div/>', {
                            id: 't' + value.substring(value.length - 1, value.length) + '-' + slot,
                            class: 'subject not-study'
                        });

                        var subjectDetailsElement = $('<div/>', {
                            class: 'subject-details'
                        });

                        slotElement.append(subjectDetailsElement);
                        dayElement.append(slotElement);
                    });

                    $('#week-subject').append(dayElement);
                });
            }
        },
        error: function (data) {
            console.error(data);
        }
    })
});