check = 0;
var _array = [
    ["","","","",""],
    ["","","","",""],
    ["","","","",""],
    ["","","","",""],
    ["","","","",""],
];
function Show(id){
    let id_value = document.getElementById(`${id}`);
    min = Math.floor(id/5);
    max = (id - min*5) ;
    if(id_value.alt == 0){
        if(check == 0){
            id_value.value = "O";
            check = 1;
            _array[min][max] = "O"
            checkWin(min, max,"O");
        }else {
            id_value.value = "X";
            id_value.style.color = "red";
            check = 0;
            _array[min][max] = "X";
            checkWin(min, max,"X");
        }
    }
    function checkWin(i, j, _value){
        // _value : 'X' or 'O'
        // Theo hang ngang: Theo i
        let count1 = 0;
        let flag = 0;
        for(let x = 0; x < 5 ; x++){
            if(_array[i][x]=='O'){
                count1++;
                if(x == 0){
                    if(_array[i][x] == _array[i][x+1] && _array[i][x] == _array[i][x+2]){
                        flag = 1;
                    }
                }
                if(x == 4){
                    if(_array[i][x] == _array[i][x-1] && _array[i][x-1] == _array[i][x-2]){
                        flag = 1;
                    }
                }
                if(x == 2){
                    if(_array[i][x] == _array[i][x+1]){
                        if(_array[i][x] == _array[i][x-1]){
                            flag = 1
                        }
                    }else {
                        if(_array[i][x] == _array[i][x-1] && _array[i][x] == _array[i][x-2]){
                            flag = 1
                        }
                    }
                }
                if(x == 3){
                    if(_array[i][x] == _array[i][x+1]){
                        if(_array[i][x] == _array[i][x-1]){
                            flag = 1
                        }
                    }else {
                        if(_array[i][x] == _array[i][x-1] && _array[i][x] == _array[i][x-2]){
                            flag = 1
                        }
                    }
                }
                if(x == 1){
                    if(_array[i][x] == _array[i][x+1]){
                        if(_array[i][x] == _array[i][x-1]){
                            flag = 1
                        }
                    }else {
                        if(_array[i][x] == _array[i][x-1] && _array[i][x] == _array[i][x-2]){
                            flag = 1
                        }
                    }
                }
            }
            
        }
        for( let y = 0 ; y < 5 ; y++){
            if(_array[y][j]=='O'){
                count1++;
                if(y == 0){
                    if(_array[y][j] == _array[y+1][j] && _array[y][j] == _array[y+2][j]){
                        flag = 1;
                    }
                }
                if(y == 4){
                    if(_array[y][j] == _array[y-1][j] && _array[y][j] == _array[y-2][j]){
                        flag = 1;
                    }
                }
                if(y == 1){
                    if(_array[y][j] == _array[y-1][j]){
                        if(_array[y][j] == _array[y+1][j]){
                            flag = 1
                        }
                    }else {
                        if(_array[y][j] == _array[y+1][j] && _array[y][j] == _array[y+2][j]){
                            flag = 1
                        }
                    }
                }
                if(y == 2){
                    if(_array[y][j] == _array[y-1][j]){
                        if(_array[y][j] == _array[y+1][j]){
                            flag = 1
                        }
                    }else {
                        if(_array[y][j] == _array[y+1][j] && _array[y][j] == _array[y+2][j]){
                            flag = 1
                        }
                    }
                }
                if(y == 3){
                    if(_array[y][j] == _array[y-1][j]){
                        if(_array[y][j] == _array[y+1][j]){
                            flag = 1
                        }
                    }else {
                        if(_array[y][j] == _array[y+1][j] && _array[y][j] == _array[y+2][j]){
                            flag = 1
                        }
                    }
                }
            }
        }
        if(count1 >=3 && flag == 1){
            alert("WIN");
            return true;
        }
        return false;
        // Theo hang doc
        // Theo hang cheo 1
        // Theo hang cheo 2
    }
}
