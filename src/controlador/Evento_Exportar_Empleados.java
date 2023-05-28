package controlador;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vista.Visualizar_Empleado;

public class Evento_Exportar_Empleados implements ActionListener{	

	
	public void actionPerformed(ActionEvent e) {
		
		
		File archivo;
		
		JFileChooser nuevo=new JFileChooser("src/Documentos Exportados/Empleados");	
		
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Documentos PDF", "pdf");
		
		nuevo.setFileFilter(filter);
		
		nuevo.requestFocusInWindow();

	    int op = nuevo.showSaveDialog(null);
	    
	    if(op==JFileChooser.APPROVE_OPTION) {
	    	
	    	archivo=nuevo.getSelectedFile();
	    	
	    	File otro_archivo=new File(archivo.getAbsolutePath()+".pdf");
	    	
	    	
	    	 
				if(otro_archivo.exists()){
					 
					 

				     JOptionPane.showMessageDialog(null,"El Archivo ya existe");
				     
				     return;

				 }else {
					 	        	 	        	 
					 
					 String ruta=archivo.getAbsolutePath();
															 
					 
					 Document doc = new Document();
					 
					try {
						
						     			
						PdfWriter.getInstance(doc, new FileOutputStream(ruta+".pdf"));
						doc.open();
						PdfPTable table = new PdfPTable(8);
						
						JTable tabla=Visualizar_Empleado.tabla;
						
						tabla.setRowHeight(35);
						
						table.setWidthPercentage(100);
						
						table.setWidths(new float[] {85,85,85,85,85,135,135,135});
						
						Paragraph titulo = new Paragraph("Empleados \n\n");
						titulo.getFont().setStyle(Font.BOLD);
						titulo.getFont().setSize(15);
						titulo.setAlignment(Element.ALIGN_CENTER);
						
						Paragraph nombre = new Paragraph("Nombre");
					    nombre.getFont().setStyle(Font.BOLD);
					    nombre.getFont().setSize(7);
					    
					    
					    Paragraph apellido1 = new Paragraph("Apellido-1");
					    apellido1.getFont().setStyle(Font.BOLD);
					    apellido1.getFont().setSize(7);
					    
					    
					    Paragraph apellido2 = new Paragraph("Apellido-2");
					    apellido2.getFont().setStyle(Font.BOLD);
					    apellido2.getFont().setSize(7);
					    
					    
					    Paragraph ci = new Paragraph("CI");
					    ci.getFont().setStyle(Font.BOLD);
					    ci.getFont().setSize(7);
					    
					    
					    Paragraph telefono = new Paragraph("Teléfono");
					    telefono.getFont().setStyle(Font.BOLD);
					    telefono.getFont().setSize(7);
					    
					    
					    Paragraph correo = new Paragraph("Correo");
					    correo.getFont().setStyle(Font.BOLD);
					    correo.getFont().setSize(7);
					    
					    
					    Paragraph especialidad = new Paragraph("Especialidad");
					    especialidad.getFont().setStyle(Font.BOLD);
					    especialidad.getFont().setSize(7);
					    
					    
					    Paragraph cargo = new Paragraph("Cargo");
					    cargo.getFont().setStyle(Font.BOLD);
					    cargo.getFont().setSize(7);
						
						
						
						
						Stream.of(nombre, apellido1, apellido2, ci, telefono, correo, especialidad, cargo).forEach(ct -> {
							PdfPCell header = new PdfPCell();
							header.setBackgroundColor(BaseColor.LIGHT_GRAY);						
							header.setBorderWidth(2);
							header.setHorizontalAlignment(Element.ALIGN_CENTER);
							header.setFixedHeight(17);
							header.setPhrase(new Phrase(ct));						
							table.addCell(header);
							
							
						});
						
						int [] seleccionada=tabla.getSelectedRows();
						
						if(seleccionada.length>0) {
							
							
							for (int i = 0; i < seleccionada.length; i++) {

								
								Paragraph columna1 = new Paragraph(tabla.getValueAt(seleccionada[i], 0).toString());
							    columna1.getFont().setStyle(Font.BOLD);
							    columna1.getFont().setSize(5);
							    
							    
							    Paragraph columna2 = new Paragraph(tabla.getValueAt(seleccionada[i], 1).toString());
							    columna2.getFont().setStyle(Font.BOLD);
							    columna2.getFont().setSize(5);
							    
							    
							    Paragraph columna3 = new Paragraph(tabla.getValueAt(seleccionada[i], 2).toString());
							    columna3.getFont().setStyle(Font.BOLD);
							    columna3.getFont().setSize(5);
							    
							    
							    Paragraph columna4 = new Paragraph(tabla.getValueAt(seleccionada[i], 3).toString());
							    columna4.getFont().setStyle(Font.BOLD);
							    columna4.getFont().setSize(5);
							    
							    
							    Paragraph columna5 = new Paragraph(tabla.getValueAt(seleccionada[i], 4).toString());
							    columna5.getFont().setStyle(Font.BOLD);
							    columna5.getFont().setSize(5);
							    
							    
							    Paragraph columna6 = new Paragraph(tabla.getValueAt(seleccionada[i], 5).toString());
							    columna6.getFont().setStyle(Font.BOLD);
							    columna6.getFont().setSize(5);
							    
							    
							    Paragraph columna7 = new Paragraph(tabla.getValueAt(seleccionada[i], 6).toString());
							    columna7.getFont().setStyle(Font.BOLD);
							    columna7.getFont().setSize(5);
							    
							    
							    Paragraph columna8 = new Paragraph(tabla.getValueAt(seleccionada[i], 7).toString());
							    columna8.getFont().setStyle(Font.BOLD);
							    columna8.getFont().setSize(5);  
								
								
								table.addCell(columna1);
								table.addCell(columna2);
								table.addCell(columna3);
								table.addCell(columna4);
								table.addCell(columna5);
								table.addCell(columna6);
								table.addCell(columna7);
								table.addCell(columna8);
							}
							
							
						}else {
							
							for (int i = 0; i < tabla.getRowCount(); i++) {

								
								Paragraph columna1 = new Paragraph(tabla.getValueAt(i, 0).toString());
							    columna1.getFont().setStyle(Font.BOLD);
							    columna1.getFont().setSize(5);
							    
							    
							    Paragraph columna2 = new Paragraph(tabla.getValueAt(i, 1).toString());
							    columna2.getFont().setStyle(Font.BOLD);
							    columna2.getFont().setSize(5);
							    
							    
							    Paragraph columna3 = new Paragraph(tabla.getValueAt(i, 2).toString());
							    columna3.getFont().setStyle(Font.BOLD);
							    columna3.getFont().setSize(5);
							    
							    
							    Paragraph columna4 = new Paragraph(tabla.getValueAt(i, 3).toString());
							    columna4.getFont().setStyle(Font.BOLD);
							    columna4.getFont().setSize(5);
							    
							    
							    Paragraph columna5 = new Paragraph(tabla.getValueAt(i, 4).toString());
							    columna5.getFont().setStyle(Font.BOLD);
							    columna5.getFont().setSize(5);
							    
							    
							    Paragraph columna6 = new Paragraph(tabla.getValueAt(i, 5).toString());
							    columna6.getFont().setStyle(Font.BOLD);
							    columna6.getFont().setSize(5);
							    
							    
							    Paragraph columna7 = new Paragraph(tabla.getValueAt(i, 6).toString());
							    columna7.getFont().setStyle(Font.BOLD);
							    columna7.getFont().setSize(5);
							    
							    
							    Paragraph columna8 = new Paragraph(tabla.getValueAt(i, 7).toString());
							    columna8.getFont().setStyle(Font.BOLD);
							    columna8.getFont().setSize(5);  
								
								
								table.addCell(columna1);
								table.addCell(columna2);
								table.addCell(columna3);
								table.addCell(columna4);
								table.addCell(columna5);
								table.addCell(columna6);
								table.addCell(columna7);
								table.addCell(columna8);
							}
							
						}
						
						
						
						doc.add(titulo);
						doc.add(table);
						doc.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Empleado exportado con exito.", null, JOptionPane.INFORMATION_MESSAGE);
					 
					 
					 
				 }
			
	    	
	    }	
		
	}

}
