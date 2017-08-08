package stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RemedianMatrix {

    public static final int DEFAUL_K = 15;
    public static final int DEFAULT_B = 11;

    private int rows;
    private int columns;

    /*  Indicates how deep we are in the RemedianMatrix.    */
    private int matrixLevel;

    /*  Double implementation.  */
    /*  This could be generalized later on. */
    private List<Double>[] matrix;

    private Object cachedMedian;

    private boolean isMatrixFilled;


    public RemedianMatrix(int rows, int columns) {

        if(rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Value for rows and columns should be positive.");
        }

        this.rows = rows;
        this.columns = columns;

        matrix = new ArrayList[rows];

        initMatrix();
    }


    public static RemedianMatrix defaultRemedianMatrix() {
        return new RemedianMatrix(DEFAUL_K, DEFAULT_B);
    }


    public void initMatrix(){
        for(int row = 0; row < rows; row++) {
            matrix[row] = null;
        }

        matrixLevel = 0;

        isMatrixFilled = false;

        cachedMedian = null;
    }


    public void addValue(Object value) {

        if(isMatrixFilled) {
            // throw new RuntimeException("Matrix is filled.");
            return;
        }

        addValue(value, 0);
        cachedMedian = null;
    }


    private void addValue(Object value, int row) {

        if(row >= rows) {
            /*  This should never happen. */
            return;
        }

        if((row + 1) > matrixLevel) {
            /*  Updating current matrix level.  */
            matrixLevel = row + 1;
        }

        if(matrix[row] == null) {
            /*  Allocating new list.    */
            matrix[row] = new ArrayList<>();
        }

        List list = matrix[row];

        list.add(value);

        if(list.size() == columns) {

            if(row == rows - 1) {
                isMatrixFilled = true;
                return;
            }

            Object median = calculateMedian(list);
            addValue(median, row + 1);
            /*  TODO    Is it probably better to remove all the elements in list?   */
            matrix[row] = new ArrayList<>();
        }
    }


    private Object calculateMedian(List<Double> list) {
        Collections.sort(list);
        return list.get(columns/2);
    }


    public Object getMedian() {
        if(cachedMedian == null) {
            if(isMatrixFilled) {
                /*  Calculate final median value.  */
                cachedMedian = calculateMedian(matrix[rows - 1]);
            } else {
                ArrayList<Double> list = new ArrayList<>();

                for(int index = 0; index < matrixLevel; index++) {

                    int weight = (int) Math.pow(columns, index);

                    List<Double> currentList = matrix[index];

                    for(int elementIndex = 0; elementIndex < currentList.size(); elementIndex++) {
                        Double currentValue = currentList.get(elementIndex);
                        for(int loopIndex = 0; loopIndex < weight; loopIndex++) {
                            /*  Adding element to the resulting list.   */
                            list.add(currentValue);
                        }
                    }
                }

                Collections.sort(list);
                cachedMedian = list.get(list.size()/2);
            }
        }

        return cachedMedian;
    }
}
