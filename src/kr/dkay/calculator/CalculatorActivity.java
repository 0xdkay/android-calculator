package kr.dkay.calculator;

//go

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
	
	private Button[] bNumber;
	private Button[] bOperator;
	private Button bClear;
	private Button bResult;
	private Button bDel;
	private Button bPoint;
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
        
        this.bClear = (Button)findViewById(R.id.ButtonC);
        this.bResult = (Button)findViewById(R.id.ButtonResult);
        this.bPoint = (Button)findViewById(R.id.ButtonPoint);
        this.bDel = (Button)findViewById(R.id.ButtonDel);
        
        this.bNumber = new Button[10];
        this.bNumber[0] = (Button)findViewById(R.id.ButtonNum0);
        this.bNumber[1] = (Button)findViewById(R.id.ButtonNum1);
        this.bNumber[2] = (Button)findViewById(R.id.ButtonNum2);
        this.bNumber[3] = (Button)findViewById(R.id.ButtonNum3);
        this.bNumber[4] = (Button)findViewById(R.id.ButtonNum4);
        this.bNumber[5] = (Button)findViewById(R.id.ButtonNum5);
        this.bNumber[6] = (Button)findViewById(R.id.ButtonNum6);
        this.bNumber[7] = (Button)findViewById(R.id.ButtonNum7);
        this.bNumber[8] = (Button)findViewById(R.id.ButtonNum8);
        this.bNumber[9] = (Button)findViewById(R.id.ButtonNum9);
        
        this.bOperator = new Button[4];
        this.bOperator[0] = (Button)findViewById(R.id.ButtonAdd);
        this.bOperator[1] = (Button)findViewById(R.id.ButtonSub);
        this.bOperator[2] = (Button)findViewById(R.id.ButtonMult);
        this.bOperator[3] = (Button)findViewById(R.id.ButtonDiv);
        
 

        
		
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
        		
        		tmp = typedView.getText().toString();
        		if(tmp.length()>20){
        			typedView.setText(tmp.substring(1, tmp.length()));
        		}
        		tmp = resultView.getText().toString();
        		if(tmp.length()>13){
        			resultView.setText(tmp.substring(1, tmp.length()));
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
        			calc();
        			operator = '+';
        			storeResultView = Float.toString(result);
        			resultView.setText(storeResultView);
        			typedView.append("+");
        			storeTypedView += "+";
        			mark = true;
        			break;
        		case R.id.ButtonSub:
        			calc();
        			operator = '-';
        			storeResultView = Float.toString(result);
        			resultView.setText(storeResultView);
        			typedView.append("-");
        			storeTypedView += "-";
        			mark = true;
        			break;
        		case R.id.ButtonMult:
        			calc();
        			operator = '*';
        			storeResultView = Float.toString(result);
        			resultView.setText(storeResultView);
        			typedView.append("*");
        			storeTypedView += "*";
        			mark = true;
        			break;
        		case R.id.ButtonDiv:
        			if(resultView.getText().toString().equals("")){
        				resultView.setText("1");
        				ad.setMessage("Input 0 changed to 1");
        				ad.show();
        			}
        			calc();
        			operator = '/';
        			storeResultView = Float.toString(result);
        			resultView.setText(storeResultView);
        			typedView.append("/");
        			storeTypedView += "/";
        			mark = true;
        			break;
        		case R.id.ButtonResult:
        			calc();
        			operator = 0;
        			storeResultView = Float.toString(result);
        			resultView.setText(storeResultView);
        			typedView.append("=");
        			storeTypedView += "=";
        			ad.setMessage(storeResultView);
        			ad.show();
        			mark = true;
        			break;
        		case R.id.ButtonDel:
        			if(!storeResultView.equals("")){
        				storeResultView = storeResultView.substring(0, storeResultView.length()-1);
        				if(storeResultView.length()>13){
        					resultView.setText(storeResultView.substring(storeResultView.length()-12, storeResultView.length()));
        				}else{
        					resultView.setText(storeResultView);
        				}
        				        				
        				storeTypedView = storeTypedView.substring(0, storeTypedView.length()-1);
        				if(storeTypedView.length()>20){
        					typedView.setText(storeTypedView.substring(storeTypedView.length()-19, storeTypedView.length()));
        				}else{
        					typedView.setText(storeTypedView);
        				}
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
        	}
        	
        	public void add(String a){
    			resultView.append(a);
    			storeResultView += a;
    			typedView.append(a);
    			storeTypedView += a;
        	}
        	
        	public void calc(){
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
        	}
        };
        
        for(int i=0; i<10; i++){
        	this.bNumber[i].setOnClickListener(adapter);
        }
        
        for(int i=0; i<4; i++){
        	this.bOperator[i].setOnClickListener(adapter);
        }
        
        this.bClear.setOnClickListener(adapter);
        this.bResult.setOnClickListener(adapter);
        this.bDel.setOnClickListener(adapter);
        this.bPoint.setOnClickListener(adapter);
    }
    
    
}

