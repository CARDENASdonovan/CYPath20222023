




Shape[][] grid = new Shape[9][9];

for(int i = 0; i<9; i++){
    for(int j = 0; j<9; j++){
          if(i%2 = 0){ // ligne 0 2 4 6 ...
            if( j%2 = 0){ // index pair de la ligne pair on veut des croix pour les coins
               grid[i][j] = new Shape(width: 100, height: 100, color: blue); //les coins
            }else{// sinon on veut faire des barrieres horizontale

            grid[i][j] = new Shape(width: 500, height: 100, color: blue);
            };
            
          }else{
            //ligne 1 3 5 ...
            if( j%2 = 0){ // index pair de la ligne impair on veut des barrieres verticale
               grid[i][j] = new Shape(width: 100, height: 100, color: blue); //les coins
            }else{ // sinon on veut des cases
              grid[i][j] = new Shape(width: 500, height: 500, color: blue);
            };
          };
        };
};