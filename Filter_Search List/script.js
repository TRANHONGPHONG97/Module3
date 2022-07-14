// Khai báo hàm chứa các biến để lấy về các giá trị
function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr"); 
    // console.log (tr);
    // Lọc về các giá kí tự không phân biệt hoa thường
    filter = input.value.toUpperCase();
  
    //Lặp qua tất cả các hàng trong bảng và ẩn những hàng không khớp với tìm kiếm
    for (i = 0; i < tr.length; i++) {
      // td = tr[i].getElementsByTagName("td")[0];
      //  => [0] tìm kiếm cột Tên, [1] tìm kiếm cột địa chỉ, [2] tìm kiếm cột tuổi
      td = tr[i].getElementsByTagName("td")[0] && tr[i].getElementsByTagName("td")[1] && tr[i].getElementsByTagName("td")[2];
      // console.log(td);
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }