package me;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;


public class Main{


	public static void main(String[] args) 
	{
		final Rechner r = new Rechner();

		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(310, 230);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Umrüstrechner");
		//frame.setResizable(false);

		JPanel panel = new JPanel();//panels
		JPanel unten = new JPanel();

		final JTextField alteleistung = new JTextField(4);	    //Text fields
		final JTextField neueleistung = new JTextField(4);
		final JTextField nutzungsdauer = new JTextField(3);	    
		final JTextField stromkosten = new JTextField("0,29ct" , 4);
		final JTextField kosten = new JTextField("30,99€" , 6);  

		JLabel alteleistunglabel = new JLabel("Alte Leistung in Watt");//labels
		JLabel neueleistunglabel = new JLabel("Neue Leistung in Watt");
		JLabel nutzungsdauerlabel = new JLabel("Nutzungsdauer pro Tag in h");
		JLabel stromkostenlabel = new JLabel("Stromkosten in cent pro kwh");
		JLabel kostenlabel = new JLabel("Umrüstungskosten in €uro");
		final JLabel ergebnis = new JLabel("Ergebnis: -");

		JButton okbtn = new JButton("Berechnen");
		okbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//nicht leer
				if( (!alteleistung.getText().isEmpty()) && (!neueleistung.getText().isEmpty()) && (!nutzungsdauer.getText().isEmpty()) && (!stromkosten.getText().isEmpty()) && (!kosten.getText().isEmpty()))
				{//nicht alteleistung mehr
					if(stringtofloat(alteleistung.getText(),false) > stringtofloat(neueleistung.getText(),false))
					{

						r.alteleistung = stringtofloat(alteleistung.getText(),false);
						r.neueleistung = stringtofloat(neueleistung.getText(),false);
						r.nutzdauer = stringtofloat(nutzungsdauer.getText(),false);
						r.stromkosten = stringtofloat(stromkosten.getText(),false);
						r.kosten = stringtofloat(kosten.getText(),false);

						ergebnis.setText("<html>Tage bis man spart: " + (int) r.rechnen() + "<br>also etwa " + r.monate + " Monate</html>");
					}
					else
					{
						ergebnis.setText("Fehlerhafte eingabe! ALte und Neuleistung Überprüfen.");
					}

				}	
				else
				{
					ergebnis.setText("Manche Eingabefelder sind Leer!");
				}
			}	

		});

		//color
		panel.setBackground(new Color(0,0,0));
		unten.setBackground(new Color(0,0,0));

		alteleistunglabel.setForeground(new Color(255,255,255));
		neueleistunglabel.setForeground(new Color(255,255,255));
		nutzungsdauerlabel.setForeground(new Color(255,255,255));
		stromkostenlabel.setForeground(new Color(255,255,255));
		kostenlabel.setForeground(new Color(255,255,255));
		ergebnis.setForeground(new Color(255,255,255));

		alteleistung.setForeground(new Color(240,240,240));
		neueleistung.setForeground(new Color(240,240,240));
		nutzungsdauer.setForeground(new Color(240,240,240));
		stromkosten.setForeground(new Color(240,240,240));
		kosten.setForeground(new Color(240,240,240));


		alteleistung.setBackground(new Color(113,113,113));
		neueleistung.setBackground(new Color(113,113,113));
		nutzungsdauer.setBackground(new Color(113,113,113));
		stromkosten.setBackground(new Color(113,113,113));
		kosten.setBackground(new Color(113,113,113));


		okbtn.setForeground(new Color(240,240,240));
		okbtn.setBackground(new Color(82,92,77));
		okbtn.setContentAreaFilled(false);
		okbtn.setOpaque(false);

		//adden
		panel.add(alteleistunglabel);
		panel.add(alteleistung);
		panel.add(neueleistunglabel);
		panel.add(neueleistung);
		panel.add(nutzungsdauerlabel);
		panel.add(nutzungsdauer);
		panel.add(stromkostenlabel);
		panel.add(stromkosten);
		panel.add(kostenlabel);
		panel.add(kosten);
		panel.add(okbtn);

		unten.add(ergebnis);

		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, panel, unten);
		split.setDividerLocation(frame.getHeight() - 60);
		split.setVisible(true);
		split.getComponent(2).setBackground(new Color(15,17,46));
		split.getComponent(2).setVisible(false);

		frame.add(split);

		frame.validate();//damit alles angezeigt wird.
		frame.repaint();
	}	



	private static float stringtofloat(String s,boolean getkomma)
	{
		float output = 0;//zwischen ergbenis
		byte zspeicher = 0;//aktuel berchnete stelle
		byte komma = 0;//stelle wo sich das komma befindet(von hinten)

		for(byte i = 0; i < s.length(); i++)
		{
			zspeicher = 0;

			switch(s.charAt(i))
			{
			case '0': zspeicher = 0;
			break;

			case '1': zspeicher = 1;
			break;

			case '2': zspeicher = 2;
			break;

			case '3': zspeicher = 3;
			break;

			case '4': zspeicher = 4;
			break;

			case '5': zspeicher = 5;
			break;

			case '6': zspeicher = 6;
			break;

			case '7': zspeicher = 7;
			break;

			case '8': zspeicher = 8;
			break;

			case '9': zspeicher = 9;
			break;

			default: if( ( ",".equals(s.charAt(i)) || ".".equals(s.charAt(i) )) && getkomma == true)//komma
			{
				komma = (byte) (s.length() - i);//stelle von hinten, wo das komma sitzt
			}
			}

			output = ( output * 10) + zspeicher;//neue ziffer hinzu fügen
		}

		if(getkomma == true)
		{
			output = output / (10^komma);//funzt net

		}


		System.out.println(output);//normale zahl
		return output;

	}
}
