<%-- 
    Document   : show
    Created on : Aug 15, 2024, 10:37:09 AM
    Author     : trucn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-3">
    <h2>Danh sách chỗ đỗ xe của bãi ${parkingLot.name}</h2>
    <!-- Calendar Navigation -->
    <div class="d-flex justify-content-around">
        <button id="prevDay">Ngày trước</button>
        <span id="currentDay"></span>
        <button id="nextDay">Ngày sau</button>
    </div>

    <!-- Calendar Pagination -->
    <div>
        <button id="prevPage">Trang trước</button>
        <button id="nextPage">Trang sau</button>
    </div>

    <!-- Calendar Container -->
    <div id='calendar'></div>

    <!-- Script to Initialize Calendar -->
    <!<!-- moment -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
    <!-- Moment-Timezone CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.34/moment-timezone-with-data.min.js"></script>
    <script> 
        
        
        
        $(document).ready(function() {
            
            
            var startTime = "${parkingLot.startTime}";
//            console.log("startTime: ", startTime)
            var endTime = "${parkingLot.endTime}";
//            console.log("endTime: ", endTime)
            var dateStart = new Date(startTime);
            var dateEnd = new Date(endTime);
            var startTime = dateStart.toTimeString().split(' ')[0];
            var endTime = dateEnd.toTimeString().split(' ')[0];

            
            
            
            var bookingList = JSON.parse('${bookingListJSON}');
            var parkingSpotList = JSON.parse('${parkingSpotListJSON}')
            console.log("bookingList: ", bookingList)
            console.log("parkingSpotList: ", parkingSpotList);
            var currentDate = new Date(); // Lấy ngày hiện tại
            var currentPage = 0; // Trang hiện tại (dành cho phân trang chỗ đỗ xe)
            var spotsPerPage = 10; // Số chỗ đỗ xe mỗi trang
            
            
//            var tempDate = currentDate.toISOString().slice(0, 10) + 'T07:40:00';
//            console.log(tempDate)
//            console.log("event: ", getEvents())
            
            
            
            // Dãy số milliseconds từ Java
//        var a = 1724292000000;
//
//        // Tạo đối tượng moment từ milliseconds và điều chỉnh múi giờ
//        var rs = moment(a).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DDTHH:mm:ss');
//
//        console.log("Formatted Date and Time: " + rs); // Kết quả: 2024-08-10T09:00:00

        let calendar;
        
       
function renderCalendar() {
//    let evnts = getEvents()
//    console.log("evnts: ", evnts)

    // Khởi tạo calendar nếu chưa tồn tại
    if (!calendar) {
        calendar = new FullCalendar.Calendar($('#calendar')[0], {
            initialView: 'resourceTimelineDay',
            headerToolbar: false, // Ẩn thanh header mặc định
            slotMinTime: startTime, // Thời gian bắt đầu
            slotMaxTime: endTime, // Thời gian kết thúc
            slotDuration: '00:30:00', // Khoảng thời gian giữa các slot
            resources: getParkingSpots(),
            events: getEvents(), // Tạo sự kiện mẫu
            height: 500, // Chiều cao lịch
            
            validRange: {
        start: moment().startOf('day').format('YYYY-MM-DD'),  // Bắt đầu từ hôm nay
        end: moment().add(30, 'days').format('YYYY-MM-DD')    // Giới hạn đến 30 ngày sau
    }
        });

        calendar.render();
    } else {
        // Xóa tất cả sự kiện hiện có
        calendar.getEvents().forEach(event => event.remove());

        // Thêm sự kiện mới
        calendar.addEventSource(getEvents());
    }

    updateCurrentDay();
}

function updateCurrentDay() {
    $('#currentDay').text(currentDate.toDateString());
}


//            function renderCalendar() {
//                console.log("rendercalendar!")
//                // Xóa calendar hiện tại nếu có
//                $('#calendar').empty();
//
//                // Khởi tạo calendar
//                var calendar = new FullCalendar.Calendar($('#calendar')[0], {
//                    initialView: 'resourceTimelineDay',
//                    headerToolbar: true, // Ẩn thanh header mặc định
//                    slotMinTime: startTime, // Thời gian bắt đầu
//                    slotMaxTime: endTime, // Thời gian kết thúc
//                    slotDuration: '00:30:00', // Khoảng thời gian giữa các slot
//                    resources: getParkingSpots(),
//                    events: getEvents(), // Tạo sự kiện mẫu
//                    height: 500 // Chiều cao lịch
//                });
//                calendar.render();
//                updateCurrentDay();
//                
//                 
//            }

            // Hàm để lấy danh sách chỗ đỗ xe theo trang
            function getParkingSpots() {
//                var spots = [
//                    { id: '1', title: 'Spot 1' },
//                    { id: '2', title: 'Spot 2' },
//                    { id: '3', title: 'Spot 3' },
//                    { id: '4', title: 'Spot 4' },
//                    { id: '5', title: 'Spot 5' },
//                    { id: '6', title: 'Spot 6' },
//                    { id: '7', title: 'Spot 7' },
//                    { id: '8', title: 'Spot 8' },
//                    { id: '9', title: 'Spot 9' },
//                    { id: '10', title: 'Spot 10' }
//                ];
                spots = parkingSpotList.map( item => ({
                    id: item.id.toString(),
                    title: item.spotNumber
                }))
                // Trả về danh sách chỗ đỗ xe theo trang
                return spots.slice(currentPage * spotsPerPage, (currentPage + 1) * spotsPerPage);
            }

            // Hàm để tạo sự kiện mẫu (bạn có thể thay thế bằng dữ liệu thực)
//            function getEvents() {
//                return bookingList.map( item => 
//                    ({
//                        start: moment(item.startTime)
//                                .tz('Asia/Ho_Chi_Minh')
//                                .format('YYYY-MM-DDTHH:mm:ss'), 
//                        end: moment(item.endTime)
//                                .tz('Asia/Ho_Chi_Minh')
//                                .format('YYYY-MM-DDTHH:mm:ss'),
//                        title: item.vehicleId.plateNumber.toString(),
//                        resourceId: item.id.toString(),
//                        id: item.id.toString()}))
//                }




function getEvents() {
    return bookingList
        .filter(item => {
            // Lấy ngày của sự kiện và ngày hiện tại của lịch
            const eventDate = moment(item.startTime).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DD');
            const selectedDay = moment(currentDate).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DD');
            // So sánh ngày của sự kiện với ngày hiện tại trên lịch
            return eventDate === selectedDay;
        })
        .map(item => {
            return {
                start: moment(item.startTime).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DDTHH:mm:ss'),
                end: moment(item.endTime).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DDTHH:mm:ss'),
                title: item.vehicleId.plateNumber.toString(),
                resourceId: item.parkingSpotId.id.toString(),
                id: item.id.toString()
            };
        });
}






//function getEvents() {
//    return bookingList
////        .filter(item => {
////            const eventDate = moment(item.startTime).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DD');
////            const currentDay = moment(currentDate).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DD');
////            return eventDate === currentDay;
////        })
//        .map(item => {
//        return {
//            start: moment(item.startTime).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DDTHH:mm:ss'), 
//            end: moment(item.endTime).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DDTHH:mm:ss'),
//            title: item.vehicleId.plateNumber.toString(),
//            resourceId: item.parkingSpotId.id.toString(),
//            id: item.id.toString()
//        }});
//}
                    
//                return [
//                    { id: '1', resourceId: '4', start: "2024-08-22T09:00:00", end: "2024-08-22T11:00:00", title: 'car A', ex:"bb" },
//                    { id: '2', resourceId: '2', start: currentDate.toISOString().slice(0, 10) + 'T11:00:00', end: currentDate.toISOString().slice(0, 10) + 'T13:00:00', title: 'Car B' }
//                ];
//            }


console.log("event: ", getEvents())

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
