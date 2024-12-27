import java.util.HashMap;

public class BayesianNetwork {
    public HashMap<String, Double> b = new HashMap<>();
    public HashMap<String, Double> e = new HashMap<>();
    public HashMap<String, Double> a = new HashMap<>();
    public HashMap<String, Double> j = new HashMap<>();
    public HashMap<String, Double> m = new HashMap<>();
    public BayesianNetwork() {
        /*
        Burglary Table Values
         */
        b.put("Bt", 0.001);

        /*
        Earthquake Table Values
         */
        e.put("Et", 0.002);

        /*
        Alarm Table Values
         */
        a.put("At|Bt,Et", 0.95);
        a.put("At|Bt,Ef", 0.94);
        a.put("At|Bf,Et", 0.29);
        a.put("At|Bf,Ef", 0.001);

        /*
        johnCalls Table Values
         */
        j.put("Jt|At", 0.90);
        j.put("Jt|Af", 0.05);

        /*
        MaryCalls Table Values
         */
        m.put("Mt|At", 0.70);
        m.put("Mt|Af", 0.01);
    }

    public double computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m) {
        Double burglary=this.b.get("Bt");
        Double earthquake=this.e.get("Et");
        Double alarm=0.0, johnCall=0.0, maryCall=0.0;
        if(b == true) {
            if(e == true) {
                alarm = this.a.get("At|Bt,Et");
            } else {
                alarm = this.a.get("At|Bt,Ef");
            }
        }
        if(b == false) {
            burglary= 1 - this.b.get("Bt");
            if(e == true) {
                alarm = this.a.get("At|Bf,Et");
            } else {
                alarm = this.a.get("At|Bf,Ef");
            }
        }
        if(e == false) {
            earthquake = 1 - this.e.get("Et");
        }
        if(a == false) {
            alarm = 1 - alarm;
            johnCall = this.j.get("Jt|Af");
            maryCall = this.m.get("Mt|Af");
        } else {
            johnCall = this.j.get("Jt|At");
            maryCall = this.m.get("Mt|At");
        }
        if(j == false) {
            johnCall = 1 - johnCall;
        }
        if(m == false) {
            maryCall = 1 - maryCall;
        }
        double ans = (burglary * earthquake * alarm * johnCall * maryCall);
        return ans;

    }
}
