<%-- 
    Document   : show
    Created on : Aug 15, 2024, 10:37:09 AM
    Author     : trucn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <h2>List parking Spots of ${parkingLot.name}</h2>
    <!-- Calendar Navigation -->
    <!-- Calendar Navigation -->
    <!-- Calendar Navigation -->
    <!-- Calendar Navigation -->
    <div>
        <button id="prevDay">Previous Day</button>
        <span id="currentDay"></span>
        <button id="nextDay">Next Day</button>
    </div>

    <!-- Calendar Pagination -->
    <div>
        <button id="prevPage">Previous Page</button>
        <button id="nextPage">Next Page</button>
    </div>

    <!-- Calendar Container -->
    <div id='calendar'></div>

    <!-- Script to Initialize Calendar -->
    <script>
        $(document).ready(function() {
            var currentDate = new Date(); // Lấy ngày hiện tại
            var currentPage = 0; // Trang hiện tại (dành cho phân trang chỗ đỗ xe)
            var spotsPerPage = 5; // Số chỗ đỗ xe mỗi trang

            function renderCalendar() {
                // Xóa calendar hiện tại nếu có
                $('#calendar').empty();

                // Khởi tạo calendar
                var calendar = new FullCalendar.Calendar($('#calendar')[0], {
                    initialView: 'resourceTimelineDay',
                    headerToolbar: false, // Ẩn thanh header mặc định
                    slotMinTime: '02:00:00', // Thời gian bắt đầu
                    slotMaxTime: '23:00:00', // Thời gian kết thúc
                    slotDuration: '00:30:00', // Khoảng thời gian giữa các slot
                    resources: getParkingSpots(),
                    events: getEvents(), // Tạo sự kiện mẫu
                    height: 650, // Chiều cao lịch
                });
                calendar.render();
                updateCurrentDay();
            }

            function updateCurrentDay() {
                $('#currentDay').text(currentDate.toDateString());
            }

            // Hàm để lấy danh sách chỗ đỗ xe theo trang
            function getParkingSpots() {
                var spots = [
                    { id: '1', title: 'Spot 1' },
                    { id: '2', title: 'Spot 2' },
                    { id: '3', title: 'Spot 3' },
                    { id: '4', title: 'Spot 4' },
                    { id: '5', title: 'Spot 5' },
                    { id: '6', title: 'Spot 6' },
                    { id: '7', title: 'Spot 7' },
                    { id: '8', title: 'Spot 8' },
                    { id: '9', title: 'Spot 9' },
                    { id: '10', title: 'Spot 10' }
                ];
                // Trả về danh sách chỗ đỗ xe theo trang
                return spots.slice(currentPage * spotsPerPage, (currentPage + 1) * spotsPerPage);
            }

            // Hàm để tạo sự kiện mẫu (bạn có thể thay thế bằng dữ liệu thực)
            function getEvents() {
                return [
                    { id: '1', resourceId: '1', start: currentDate.toISOString().slice(0, 10) + 'T07:40:00', end: currentDate.toISOString().slice(0, 10) + 'T09:00:00', title: '${parkingLot.name}' },
                    { id: '2', resourceId: '2', start: currentDate.toISOString().slice(0, 10) + 'T11:00:00', end: currentDate.toISOString().slice(0, 10) + 'T13:00:00', title: 'Car B' }
                ];
            }

            // Xử lý nút ngày trước
            $('#prevDay').on('click', function() {
                currentDate.setDate(currentDate.getDate() - 1);
                renderCalendar();
            });

            // Xử lý nút ngày tiếp theo
            $('#nextDay').on('click', function() {
                currentDate.setDate(currentDate.getDate() + 1);
                renderCalendar();
            });

            // Xử lý nút trang trước (phân trang chỗ đỗ xe)
            $('#prevPage').on('click', function() {
                if (currentPage > 0) {
                    currentPage--;
                    renderCalendar();
                }
            });

            // Xử lý nút trang tiếp theo (phân trang chỗ đỗ xe)
            $('#nextPage').on('click', function() {
                if ((currentPage + 1) * spotsPerPage < 10) { // 10 là tổng số chỗ đỗ xe
                    currentPage++;
                    renderCalendar();
                }
            });

            // Khởi tạo lịch khi trang được tải lần đầu
            renderCalendar();
        });
    </script>
</div>
