package controlador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vista.Visualizar_Orden_Trabajo;
import vista.Visualizar_Reporte_Averia;

public class Evento_Exportar_Reporte_Averia implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		
		
		File archivo;
		
		JFileChooser nuevo=new JFileChooser("src/Documentos Exportados/Reporte de Averia");	
		
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
					 
					 String ruta_archivo=archivo.getAbsolutePath();
					 
					 	Document doc = new Document();
						try {
							PdfWriter.getInstance(doc, new FileOutputStream(ruta_archivo+".pdf"));
							doc.open();
							
							JTable tabla=Visualizar_Reporte_Averia.tabla;
										
							PdfPTable table = new PdfPTable(6);			
										
							int num_filas=tabla.getRowCount();
									
							tabla.setRowHeight(35);
							
							table.setWidthPercentage(100);
							
							table.setWidths(new float[] {15,40,40,40,40,40});
							
							Paragraph titulo = new Paragraph("Reportes de Averias \n\n");
							titulo.getFont().setStyle(Font.BOLD);
							titulo.getFont().setSize(15);
							titulo.setAlignment(Element.ALIGN_CENTER);
							
							Paragraph id = new Paragraph("ID");
							id.getFont().setStyle(Font.BOLD);
							id.getFont().setSize(7);
							id.setAlignment(Element.ALIGN_CENTER);
							
							Paragraph fecha = new Paragraph("Fecha Reporte");
							fecha.getFont().setStyle(Font.BOLD);
							fecha.getFont().setSize(7);
							fecha.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph cod_equipo = new Paragraph("Codigo Equipo");
						    cod_equipo.getFont().setStyle(Font.BOLD);
						    cod_equipo.getFont().setSize(7);
						    cod_equipo.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph nom_equipo = new Paragraph("Nombre Equipo");
						    nom_equipo.getFont().setStyle(Font.BOLD);
						    nom_equipo.getFont().setSize(7);
						    nom_equipo.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph ubi_equipo = new Paragraph("Ubicacion Equipo");
						    ubi_equipo.getFont().setStyle(Font.BOLD);
						    ubi_equipo.getFont().setSize(7);
						    ubi_equipo.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph estado_tarea = new Paragraph("Estado Tarea");
						    estado_tarea.getFont().setStyle(Font.BOLD);
						    estado_tarea.getFont().setSize(7);
						    estado_tarea.setAlignment(Element.ALIGN_CENTER);
						    
						      
							
							
							
							
							Stream.of(id, fecha, cod_equipo, nom_equipo, ubi_equipo, estado_tarea).forEach(ct -> {
								PdfPCell header = new PdfPCell();
								header.setBackgroundColor(BaseColor.LIGHT_GRAY);						
								header.setBorderWidth(2);
								header.setHorizontalAlignment(Element.ALIGN_CENTER);
								header.setFixedHeight(17);
								header.setPhrase(new Phrase(ct));						
								table.addCell(header);
								
								
							});
							
							int [] seleccionadas=tabla.getSelectedRows();
							
							if(seleccionadas.length>0) {			
								
								
								System.out.println("primer condicional");
								
							}else {
								
								for (int i = 0; i < tabla.getRowCount(); i++) {

									
									Paragraph columna1 = new Paragraph(tabla.getValueAt(i, 0).toString());
								    columna1.getFont().setStyle(Font.BOLD);
								    columna1.getFont().setSize(5);
								    columna1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna2 = new Paragraph(tabla.getValueAt(i, 1).toString());
								    columna2.getFont().setStyle(Font.BOLD);
								    columna2.getFont().setSize(5);
								    columna2.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna3 = new Paragraph(tabla.getValueAt(i, 4).toString());
								    columna3.getFont().setStyle(Font.BOLD);
								    columna3.getFont().setSize(5);
								    columna3.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna4 = new Paragraph(tabla.getValueAt(i, 5).toString());
								    columna4.getFont().setStyle(Font.BOLD);
								    columna4.getFont().setSize(5);
								    columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna5 = new Paragraph(tabla.getValueAt(i, 6).toString());
								    columna5.getFont().setStyle(Font.BOLD);
								    columna5.getFont().setSize(5);
								    columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna6 = new Paragraph(tabla.getValueAt(i, 7).toString());
								    columna6.getFont().setStyle(Font.BOLD);
								    columna6.getFont().setSize(5);
								    columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    		   
								   				
									
									table.addCell(columna1);
									table.addCell(columna2);
									table.addCell(columna3);
									table.addCell(columna4);
									table.addCell(columna5);
									table.addCell(columna6);
									
									
									System.out.println("segundo condicional");
									
									
								}
								
							}
							
							
							
							
							if(num_filas>1 && tabla.getSelectedRows().length!=1) {				
								
								doc.add(titulo);
								doc.add(table);
								doc.close();
								
								System.out.println("tercer condicional");
								
							}else if(tabla.getSelectedRows().length==1 || num_filas<=1){	
								
								
								table.resetColumnCount(6);
								
								table.setWidths(new float[] {15,40,40,40,40,40});
								
								System.out.println("cuarto condicional");
													
								

									
								Paragraph columna1 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
								columna1.getFont().setStyle(Font.BOLD);
								columna1.getFont().setSize(5);
								columna1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna2 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
								columna2.getFont().setStyle(Font.BOLD);
								columna2.getFont().setSize(5);
								columna2.setAlignment(Element.ALIGN_CENTER);
								    				    	    
								    
								    
								Paragraph columna4 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 4).toString());
								columna4.getFont().setStyle(Font.BOLD);
								columna4.getFont().setSize(5);
								columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna5 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 5).toString());
								columna5.getFont().setStyle(Font.BOLD);
								columna5.getFont().setSize(5);
								columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna6 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 6).toString());
								columna6.getFont().setStyle(Font.BOLD);
								columna6.getFont().setSize(5);
								columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna7 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 7).toString());
								columna7.getFont().setStyle(Font.BOLD);
								columna7.getFont().setSize(5);
								columna7.setAlignment(Element.ALIGN_CENTER);		    
								    
								
								    
								    
								   
								   				
									
								table.addCell(columna1);
								table.addCell(columna2);					
								table.addCell(columna4);
								table.addCell(columna5);
								table.addCell(columna6);
								table.addCell(columna7);
								
								
								
								
								
								
								
								
								
								//---------------------------------------------------------Equipos------------------------------------------------------------------------------
								
								
								
								/*Paragraph titulo_equipos = new Paragraph("\n Equipo"+"\n\n");
								titulo_equipos.getFont().setStyle(Font.BOLD);
								titulo_equipos.getFont().setSize(10);
								titulo_equipos.setAlignment(Element.ALIGN_CENTER);
								
								
								JTable tabla_equipos=Visualizar_Orden_Trabajo.equipos;
								
								PdfPTable table_equipos = new PdfPTable(4);
								
								table_equipos.setWidthPercentage(100);
								
								table_equipos.setWidths(new float[] {50,50,50,50});
								
								Paragraph nombre = new Paragraph("Nombre");
								nombre.getFont().setStyle(Font.BOLD);
								nombre.getFont().setSize(7);
								nombre.setAlignment(Element.ALIGN_CENTER);
								
								Paragraph marca = new Paragraph("Marca");
								marca.getFont().setStyle(Font.BOLD);
								marca.getFont().setSize(7);
								marca.setAlignment(Element.ALIGN_CENTER);
							    
							    Paragraph codigo = new Paragraph("Codigo");
							    codigo.getFont().setStyle(Font.BOLD);
							    codigo.getFont().setSize(7);
							    codigo.setAlignment(Element.ALIGN_CENTER);
							    
							    Paragraph ubicacion = new Paragraph("Ubicacion");
							    ubicacion.getFont().setStyle(Font.BOLD);
							    ubicacion.getFont().setSize(7);
							    ubicacion.setAlignment(Element.ALIGN_CENTER);
								
								
							    Stream.of(nombre, marca, codigo, ubicacion).forEach(ct -> {
									PdfPCell header = new PdfPCell();
									header.setBackgroundColor(BaseColor.LIGHT_GRAY);						
									header.setBorderWidth(2);
									header.setHorizontalAlignment(Element.ALIGN_CENTER);
									header.setFixedHeight(17);
									header.setPhrase(new Phrase(ct));						
									table_equipos.addCell(header);
									
									
								});	    
							    
							    
							    
							    
							    for (int i = 0; i < tabla_equipos.getRowCount(); i++) {

									
									Paragraph c_equipo1 = new Paragraph(tabla_equipos.getValueAt(i, 0).toString());
									c_equipo1.getFont().setStyle(Font.BOLD);
									c_equipo1.getFont().setSize(5);
									c_equipo1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph c_equipo2 = new Paragraph(tabla_equipos.getValueAt(i, 1).toString());
								    c_equipo2.getFont().setStyle(Font.BOLD);
								    c_equipo2.getFont().setSize(5);
								    c_equipo2.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph c_equipo3 = new Paragraph(tabla_equipos.getValueAt(i, 2).toString());
								    c_equipo3.getFont().setStyle(Font.BOLD);
								    c_equipo3.getFont().setSize(5);
								    c_equipo3.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph c_equipo4 = new Paragraph(tabla_equipos.getValueAt(i, 3).toString());
								    c_equipo4.getFont().setStyle(Font.BOLD);
								    c_equipo4.getFont().setSize(5);
								    c_equipo4.setAlignment(Element.ALIGN_CENTER);
								    
								    table_equipos.addCell(c_equipo1);
								    table_equipos.addCell(c_equipo2);
								    table_equipos.addCell(c_equipo3);
								    table_equipos.addCell(c_equipo4);
							    
							    }*/
							    
							    
							    
							    
							    
							    //---------------------------------------------------------Empleados-------------------------------------------------------------
							    
							    
							    
							    
							    /*Paragraph titulo_empleados = new Paragraph("\n Empleados"+"\n\n");
							    titulo_empleados.getFont().setStyle(Font.BOLD);
							    titulo_empleados.getFont().setSize(10);
							    titulo_empleados.setAlignment(Element.ALIGN_CENTER);
								
								
								JTable tabla_empleados=Visualizar_Orden_Trabajo.empleados;
								
								PdfPTable table_empleados = new PdfPTable(4);
								
								table_empleados.setWidthPercentage(100);
								
								table_empleados.setWidths(new float[] {50,50,50,50});
								
								Paragraph nombre_empleado = new Paragraph("Nombre");
								nombre_empleado.getFont().setStyle(Font.BOLD);
								nombre_empleado.getFont().setSize(7);
								nombre_empleado.setAlignment(Element.ALIGN_CENTER);
								
								Paragraph apellido1 = new Paragraph("Apellido 1");
								apellido1.getFont().setStyle(Font.BOLD);
								apellido1.getFont().setSize(7);
								apellido1.setAlignment(Element.ALIGN_CENTER);
							    
							    Paragraph cargo = new Paragraph("Cargo");
							    cargo.getFont().setStyle(Font.BOLD);
							    cargo.getFont().setSize(7);
							    cargo.setAlignment(Element.ALIGN_CENTER);
							    
							    Paragraph ci = new Paragraph("CI");
							    ci.getFont().setStyle(Font.BOLD);
							    ci.getFont().setSize(7);
							    ci.setAlignment(Element.ALIGN_CENTER);
								
								
							    Stream.of(nombre_empleado, apellido1, cargo, ci).forEach(ct -> {
									PdfPCell header = new PdfPCell();
									header.setBackgroundColor(BaseColor.LIGHT_GRAY);						
									header.setBorderWidth(2);
									header.setHorizontalAlignment(Element.ALIGN_CENTER);
									header.setFixedHeight(17);
									header.setPhrase(new Phrase(ct));						
									table_empleados.addCell(header);
									
									
								});
							    
							    
							    for (int i = 0; i < tabla_empleados.getRowCount(); i++) {

									
									Paragraph c_empleado1 = new Paragraph(tabla_empleados.getValueAt(i, 0).toString());
									c_empleado1.getFont().setStyle(Font.BOLD);
									c_empleado1.getFont().setSize(5);
									c_empleado1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph c_empleado2 = new Paragraph(tabla_empleados.getValueAt(i, 1).toString());
								    c_empleado2.getFont().setStyle(Font.BOLD);
								    c_empleado2.getFont().setSize(5);
								    c_empleado2.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph c_empleado3 = new Paragraph(tabla_empleados.getValueAt(i, 2).toString());
								    c_empleado3.getFont().setStyle(Font.BOLD);
								    c_empleado3.getFont().setSize(5);
								    c_empleado3.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph c_empleado4 = new Paragraph(tabla_empleados.getValueAt(i, 3).toString());
								    c_empleado4.getFont().setStyle(Font.BOLD);
								    c_empleado4.getFont().setSize(5);
								    c_empleado4.setAlignment(Element.ALIGN_CENTER);
								    
								    table_empleados.addCell(c_empleado1);
								    table_empleados.addCell(c_empleado2);
								    table_empleados.addCell(c_empleado3);
								    table_empleados.addCell(c_empleado4);
							    
							    }*/
							    
							    
							    
							    
							    
							    //----------------------------------------------***************************************--------------------------------------------
							    
								
							    Paragraph contenedor = new Paragraph();
							    
							    int row=tabla.getSelectedRow();
							    
							    
							    
							    
							    String descripcion=tabla.getValueAt(row, 2).toString();
							    
							    			    
							    		    
								
							    Paragraph titulo_descripcion = new Paragraph("\n Descripcion de la Averia: \n");		    		
							    		
							    		
							    titulo_descripcion.getFont().setStyle(Font.BOLD);
							    titulo_descripcion.getFont().setSize(10);
							    titulo_descripcion.setAlignment(Element.ALIGN_LEFT);
							    
							    
							    titulo_descripcion.add(descripcion);
							    
							    titulo_descripcion.getFont().setStyle(Font.PLAIN);	
							    
							    
							    
							    
							    String causas=tabla.getValueAt(row, 3).toString();
							    
							    String array_causas[]= causas.split("\n");
							    
							    List list_causas = new List(List.UNORDERED);
							    
							   
							    
							    for(int i=0; i<array_causas.length; i++) {
							    	
							    	list_causas.add(array_causas[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_causas = new Paragraph("Posibles causas de la Averia:");			    		
							    		
							    		    		
							    titulo_causas.getFont().setStyle(Font.BOLD);
							    titulo_causas.getFont().setSize(12);
							    titulo_causas.setAlignment(Element.ALIGN_CENTER);
							    
							    
							    titulo_causas.add(list_causas);	
							    
							    titulo_causas.getFont().setStyle(Font.PLAIN);
							    
							     			   			    
							    
							    
							    contenedor.add(titulo_descripcion);
							    contenedor.add(titulo_causas);
							    
							    
								
									
								
								doc.add(titulo);								
								doc.add(table);
								doc.add(contenedor);
								
								
								doc.close();
								
							}
							
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
							
						} catch (DocumentException e1) {
							
							e1.printStackTrace();
							
						} catch (Exception e1) {
							
							JOptionPane.showMessageDialog(null, "Seleccione un Reporte de Averia para exportar.");
							
							return;
							
						}
						
						JOptionPane.showMessageDialog(null, "Reporte de Averia exportados con exito.", null, JOptionPane.INFORMATION_MESSAGE);
					 
				 }
	    }
		
		
	

		
	}

}
