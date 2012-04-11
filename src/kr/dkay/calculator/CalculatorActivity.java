package kr.dkay.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class CalculatorActivity extends Activity {

	private TextView resultView;
	String storeResultView = "";
	String storeTypedView = "";
	private TextView typedView;
	
	private Context context = this;
	
	private char operator;
	private float result;
	private boolean mark; 
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        result = 0;
        operator = 0;
        mark = true;
        
        this.resultView = (TextView)findViewById(R.id.result);
        this.typedView = (TextView)findViewById(R.id.typed);
		
        Button.OnClickListener adapter = new Button.OnClickListener() {
        	
        	String tmp;
        	AlertDialog.Builder ad = new AlertDialog.Builder(context);
        	public void onClick(View v){

        	    ad.setTitle("Result");
        		ad.setCancelable(true);
        		ad.setPositiveButton("Close", null);
        		
        		if(mark){
        			resultView.setText("");
        			storeResultView = "";
        			mark = false;
        		}
        		
        		switch(v.getId()){
        		case R.id.ButtonNum0:
        			add("0");
    				break;
        		case R.id.ButtonNum1:
        			add("1");
        			break;
        		case R.id.ButtonNum2:
        			add("2");
        			break;
        		case R.id.ButtonNum3:
        			add("3");
        			break;
        		case R.id.ButtonNum4:
        			add("4");
        			break;
        		case R.id.ButtonNum5:
        			add("5");
        			break;
        		case R.id.ButtonNum6:
        			add("6");
        			break;
        		case R.id.ButtonNum7:
        			add("7");
        			break;
        		case R.id.ButtonNum8:
        			add("8");
        			break;
        		case R.id.ButtonNum9:
        			add("9");
        			break;
        		case R.id.ButtonAdd:
        			calc("+");
        			break;
        		case R.id.ButtonSub:
        			calc("-");
        			break;
        		case R.id.ButtonMult:
        			calc("*");
        			break;
        		case R.id.ButtonDiv:
        			if(resultView.getText().toString().equals("")){
        				resultView.setText("1");
        				ad.setMessage("Input 0 changed to 1");
        				ad.show();
        			}
        			calc("/");
        			break;
        		case R.id.ButtonResult:
        			calc("=");
        			ad.setMessage(storeResultView);
        			ad.show();
        			break;
        		case R.id.ButtonDel:
        			if(!storeResultView.equals("")){
        				if(storeResultView.length()>13){
        					resultView.setText(storeResultView.substring(storeResultView.length()-14, storeResultView.length()-1));
        				}else{
        					resultView.setText(storeResultView.substring(0, storeResultView.length()-1));
        				}
        				storeResultView = storeResultView.substring(0, storeResultView.length()-1);
        				        				
        				if(storeTypedView.length()>20){
        					typedView.setText(storeTypedView.substring(storeTypedView.length()-21, storeTypedView.length()-1));
        				}else{
        					typedView.setText(storeTypedView.substring(0, storeTypedView.length()-1));
        				}
        				storeTypedView = storeTypedView.substring(0, storeTypedView.length()-1);
        			}
        			break;
        		case R.id.ButtonC:
        			result = 0;
        			operator = 0;
        			resultView.setText("");
        			storeResultView = "";
        			typedView.setText("");
        			storeTypedView = "";
        			break;
        		case R.id.ButtonPoint:
        			tmp = resultView.getText().toString();
        			if(tmp.indexOf('.')==-1){
        				resultView.setText(tmp+".");
        				storeResultView += ".";
        			}
        			typedView.append(".");
        			storeTypedView += ".";
        			break;
        		}
        		
        		tmp = typedView.getText().toString();
        		if(tmp.length()>20){
        			typedView.setText(tmp.substring(1, tmp.length()));
        		}
        		tmp = resultView.getText().toString();
        		if(tmp.length()>13){
        			resultView.setText(tmp.substring(1, tmp.length()));
        		}
        		
        	}
        	
        	public void add(String a){
    			resultView.append(a);
    			storeResultView += a;
    			typedView.append(a);
    			storeTypedView += a;
        	}
        	
        	public void calc(String a){
        		if(!storeResultView.equals("")){
    				switch(operator){
    				case '+':
    					result += Float.valueOf(storeResultView).floatValue();
    					break;
   					case '-':
   						result -= Float.valueOf(storeResultView).floatValue();
   						break;
   					case '*':
   						result *= Float.valueOf(storeResultView).floatValue();
   						break;
   					case '/':
   						result /= Float.valueOf(storeResultView).floatValue();
   						break; 
   					default:
   						result = Float.valueOf(storeResultView).floatValue();
   						break;
   					}
    			}
        		
    			if(a.equals("=")){
    				operator = 0;
    			}else{
    				operator = a.charAt(0);
    			}
    			storeResultView = Float.toString(result);
    			resultView.setText(storeResultView);
    			typedView.append(a);
    			storeTypedView += a;
    			mark = true;
        	}
        };
        
        findViewById(R.id.ButtonC).setOnClickListener(adapter);
        findViewById(R.id.ButtonResult).setOnClickListener(adapter);
        findViewById(R.id.ButtonPoint).setOnClickListener(adapter);
        findViewById(R.id.ButtonDel).setOnClickListener(adapter);
        
        findViewById(R.id.ButtonNum0).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum1).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum2).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum3).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum4).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum5).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum6).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum7).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum8).setOnClickListener(adapter);
        findViewById(R.id.ButtonNum9).setOnClickListener(adapter);
        
        findViewById(R.id.ButtonAdd).setOnClickListener(adapter);
        findViewById(R.id.ButtonSub).setOnClickListener(adapter);
        findViewById(R.id.ButtonMult).setOnClickListener(adapter);
        findViewById(R.id.ButtonDiv).setOnClickListener(adapter);
    }
}

