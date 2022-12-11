package blackbox.cases.basehalstead;

/*
 * n1=14	N1=54	n2=11	N2=39
 * Length = 93, Difficulty = 24.82, Effort = 10719.2616, Vocabulary = 25, Volume = 413.88
 * based on https://www.geeksforgeeks.org/software-engineering-halsteads-software-metrics/
 * added 1 to N1, n2, and N2 to account for the included class definition and bracket.
 */

public class HalsteadMetricTestCase {
	
	int sort (int x[ ], int n)
	{
	    int i, j, save, im1;
	    /*This function sorts array x in ascending order */
	    if (n< 2) return 1;
	    for (i=2; i <= n; i++)
	    {
	        im1=i-1;
	        for (j=1; j <= im1; j++)
	            if (x[i] < x[j])
	            {
	                save = x[i];
	                x[i] = x[j];
	                x[j] = save;
	            }
	    }
	    return 0;
	}
}
