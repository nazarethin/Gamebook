
package kk.calcul;

/**
 * @author https://www.programiz.com/dsa/floyd-warshall-algorithm#:~:text=Floyd%2DWarshall%20Algorithm%20is%20an,in%20a%20cycle%20is%20negative).
 */

class FloydWarshall {
    final static int INF = 9999;
  
    public int graph[][];
  
    public FloydWarshall(int graph[][], int nV){
		int matrix[][] = new int[nV][nV];
		int i, j, k;
		
		for (i = 0; i < nV; i++)
		    for (j = 0; j < nV; j++)
				matrix[i][j] = graph[i][j];

		// Adding vertices individually
		for (k = 0; k < nV; k++){
		    for (i = 0; i < nV; i++){
				for (j = 0; j < nV; j++){
					if (matrix[i][k] + matrix[k][j] < matrix[i][j])
						matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}	 
		this.graph = matrix;
    }
}