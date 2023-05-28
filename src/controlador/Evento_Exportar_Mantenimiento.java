package controlador;

import java.awt.Font;
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

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import vista.Visualizar_Equipo;
import vista.Visualizar_Plan_Mantenimiento;

public class Evento_Exportar_Mantenimiento implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		
		
		File archivo;
		
		JFileChooser nuevo=new JFileChooser("src/Documentos Exportados/Plan Mantenimiento");	
		
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
							
							JTable tabla=Visualizar_Plan_Mantenimiento.tabla;
										
							PdfPTable table = new PdfPTable(10);			
										
							int num_filas=tabla.getRowCount();
									
							tabla.setRowHeight(35);
							
							table.setWidthPercentage(100);
							
							table.setWidths(new float[] {15,35,40,55,45,60,50,40,30,30});
							
							Paragraph titulo = new Paragraph("Planes de Mantenimiento \n\n");
							titulo.getFont().setStyle(Font.BOLD);
							titulo.getFont().setSize(15);
							titulo.setAlignment(Element.ALIGN_CENTER);
							
							Paragraph id = new Paragraph("ID");
							id.getFont().setStyle(Font.BOLD);
							id.getFont().setSize(7);
							id.setAlignment(Element.ALIGN_CENTER);
							
							Paragraph fecha_inicio = new Paragraph("Fecha Inicio");
							fecha_inicio.getFont().setStyle(Font.BOLD);
							fecha_inicio.getFont().setSize(7);
							fecha_inicio.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph codigo_equipo = new Paragraph("Código Equipo");
						    codigo_equipo.getFont().setStyle(Font.BOLD);
						    codigo_equipo.getFont().setSize(7);
						    codigo_equipo.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tipo_mantenimiento = new Paragraph("Tipo Mantenimiento");
						    tipo_mantenimiento.getFont().setStyle(Font.BOLD);
						    tipo_mantenimiento.getFont().setSize(7);
						    tipo_mantenimiento.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph frecuencia = new Paragraph("Frecuencia");
						    frecuencia.getFont().setStyle(Font.BOLD);
						    frecuencia.getFont().setSize(7);
						    frecuencia.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tiempo_preparacion = new Paragraph("Tiempo Preparación");
						    tiempo_preparacion.getFont().setStyle(Font.BOLD);
						    tiempo_preparacion.getFont().setSize(7);
						    tiempo_preparacion.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tiempo_estimado = new Paragraph("Tiempo Estimado");
						    tiempo_estimado.getFont().setStyle(Font.BOLD);
						    tiempo_estimado.getFont().setSize(7);
						    tiempo_estimado.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tiempo_real = new Paragraph("Tiempo Real");
						    tiempo_real.getFont().setStyle(Font.BOLD);
						    tiempo_real.getFont().setSize(7);
						    tiempo_real.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tipo_tarea = new Paragraph("Tipo Tarea");
						    tipo_tarea.getFont().setStyle(Font.BOLD);
						    tipo_tarea.getFont().setSize(7);
						    tipo_tarea.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tarea_realizada = new Paragraph("Realizado");
						    tarea_realizada.getFont().setStyle(Font.BOLD);
						    tarea_realizada.getFont().setSize(7);
						    tarea_realizada.setAlignment(Element.ALIGN_CENTER);
						    
							
							
							
							
							Stream.of(id, fecha_inicio, codigo_equipo, tipo_mantenimiento, frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, tarea_realizada).forEach(ct -> {
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
								
								for (int i = 0; i < seleccionadas.length; i++) {

									
									Paragraph columna1 = new Paragraph(tabla.getValueAt(seleccionadas[i], 0).toString());
								    columna1.getFont().setStyle(Font.BOLD);
								    columna1.getFont().setSize(5);
								    columna1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna2 = new Paragraph(tabla.getValueAt(seleccionadas[i], 1).toString());
								    columna2.getFont().setStyle(Font.BOLD);
								    columna2.getFont().setSize(5);
								    columna2.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna3 = new Paragraph(tabla.getValueAt(seleccionadas[i], 2).toString());
								    columna3.getFont().setStyle(Font.BOLD);
								    columna3.getFont().setSize(5);
								    columna3.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna4 = new Paragraph(tabla.getValueAt(seleccionadas[i], 3).toString());
								    columna4.getFont().setStyle(Font.BOLD);
								    columna4.getFont().setSize(5);
								    columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna5 = new Paragraph(tabla.getValueAt(seleccionadas[i], 7).toString());
								    columna5.getFont().setStyle(Font.BOLD);
								    columna5.getFont().setSize(5);
								    columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna6 = new Paragraph(tabla.getValueAt(seleccionadas[i], 8).toString());
								    columna6.getFont().setStyle(Font.BOLD);
								    columna6.getFont().setSize(5);
								    columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna7 = new Paragraph(tabla.getValueAt(seleccionadas[i], 9).toString());
								    columna7.getFont().setStyle(Font.BOLD);
								    columna7.getFont().setSize(5);
								    columna7.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna8 = new Paragraph(tabla.getValueAt(seleccionadas[i], 10).toString());
								    columna8.getFont().setStyle(Font.BOLD);
								    columna8.getFont().setSize(5);
								    columna8.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna9 = new Paragraph(tabla.getValueAt(seleccionadas[i], 11).toString());
								    columna9.getFont().setStyle(Font.BOLD);
								    columna9.getFont().setSize(5);
								    columna9.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    
								    Paragraph columna10 = new Paragraph(tabla.getValueAt(seleccionadas[i], 12).toString());
								    columna10.getFont().setStyle(Font.BOLD);
								    columna10.getFont().setSize(5);
								    columna10.setAlignment(Element.ALIGN_CENTER);
								    
								   
								   				
									
									table.addCell(columna1);
									table.addCell(columna2);
									table.addCell(columna3);
									table.addCell(columna4);
									table.addCell(columna5);
									table.addCell(columna6);
									table.addCell(columna7);
									table.addCell(columna8);
									table.addCell(columna9);
									table.addCell(columna10);
									
								}
								
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
								    
								    
								    Paragraph columna3 = new Paragraph(tabla.getValueAt(i, 2).toString());
								    columna3.getFont().setStyle(Font.BOLD);
								    columna3.getFont().setSize(5);
								    columna3.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna4 = new Paragraph(tabla.getValueAt(i, 3).toString());
								    columna4.getFont().setStyle(Font.BOLD);
								    columna4.getFont().setSize(5);
								    columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna5 = new Paragraph(tabla.getValueAt(i, 7).toString());
								    columna5.getFont().setStyle(Font.BOLD);
								    columna5.getFont().setSize(5);
								    columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna6 = new Paragraph(tabla.getValueAt(i, 8).toString());
								    columna6.getFont().setStyle(Font.BOLD);
								    columna6.getFont().setSize(5);
								    columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna7 = new Paragraph(tabla.getValueAt(i, 9).toString());
								    columna7.getFont().setStyle(Font.BOLD);
								    columna7.getFont().setSize(5);
								    columna7.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna8 = new Paragraph(tabla.getValueAt(i, 10).toString());
								    columna8.getFont().setStyle(Font.BOLD);
								    columna8.getFont().setSize(5);
								    columna8.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna9 = new Paragraph(tabla.getValueAt(i, 11).toString());
								    columna9.getFont().setStyle(Font.BOLD);
								    columna9.getFont().setSize(5);
								    columna9.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna10 = new Paragraph(tabla.getValueAt(i, 12).toString());
								    columna10.getFont().setStyle(Font.BOLD);
								    columna10.getFont().setSize(5);
								    columna10.setAlignment(Element.ALIGN_CENTER);
								    
								    
								   
								   				
									
									table.addCell(columna1);
									table.addCell(columna2);
									table.addCell(columna3);
									table.addCell(columna4);
									table.addCell(columna5);
									table.addCell(columna6);
									table.addCell(columna7);
									table.addCell(columna8);
									table.addCell(columna9);
									table.addCell(columna10);
									
								}
								
							}
							
							
							
							
							if(num_filas>1 && tabla.getSelectedRows().length!=1) {				
								
								doc.add(titulo);
								doc.add(table);
								doc.close();
								
								System.out.println("Primero");
								
							}else if(tabla.getSelectedRows().length==1 || num_filas<=1){	
								
								
								table.resetColumnCount(9);
								
								table.setWidths(new float[] {15,35,55,40,60,50,40,30,30});
								
								System.out.println("Entro");
								
								
								Stream.of(id, fecha_inicio, tipo_mantenimiento, frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real, tipo_tarea, tarea_realizada).forEach(ct -> {
									PdfPCell header = new PdfPCell();
									header.setBackgroundColor(BaseColor.LIGHT_GRAY);						
									header.setBorderWidth(2);
									header.setHorizontalAlignment(Element.ALIGN_CENTER);
									header.setFixedHeight(17);
									header.setPhrase(new Phrase(ct));						
									table.addCell(header);
									
									
								});
								
								

									
								Paragraph columna1 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
								columna1.getFont().setStyle(Font.BOLD);
								columna1.getFont().setSize(5);
								columna1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna2 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
								columna2.getFont().setStyle(Font.BOLD);
								columna2.getFont().setSize(5);
								columna2.setAlignment(Element.ALIGN_CENTER);
								    				    	    
								    
								    
								Paragraph columna4 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
								columna4.getFont().setStyle(Font.BOLD);
								columna4.getFont().setSize(5);
								columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna5 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 7).toString());
								columna5.getFont().setStyle(Font.BOLD);
								columna5.getFont().setSize(5);
								columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna6 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 8).toString());
								columna6.getFont().setStyle(Font.BOLD);
								columna6.getFont().setSize(5);
								columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna7 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 9).toString());
								columna7.getFont().setStyle(Font.BOLD);
								columna7.getFont().setSize(5);
								columna7.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna8 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 10).toString());
								columna8.getFont().setStyle(Font.BOLD);
								columna8.getFont().setSize(5);
								columna8.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna9 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 11).toString());
								columna9.getFont().setStyle(Font.BOLD);
								columna9.getFont().setSize(5);
								columna9.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna10 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 12).toString());
								columna10.getFont().setStyle(Font.BOLD);
								columna10.getFont().setSize(5);
								columna10.setAlignment(Element.ALIGN_CENTER);
								    
								    
								   
								   				
									
								table.addCell(columna1);
								table.addCell(columna2);					
								table.addCell(columna4);
								table.addCell(columna5);
								table.addCell(columna6);
								table.addCell(columna7);
								table.addCell(columna8);
								table.addCell(columna9);
								table.addCell(columna10);
								
								
								
								//-----------------------***********************************************************************-------------------------
								
								
								
								Paragraph titulo_equipos = new Paragraph("\n Equipos"+"\n\n");
								titulo_equipos.getFont().setStyle(Font.BOLD);
								titulo_equipos.getFont().setSize(10);
								titulo_equipos.setAlignment(Element.ALIGN_CENTER);
								
								
								JTable tabla_equipos=Visualizar_Plan_Mantenimiento.equipos;
								
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
							    
							    Paragraph codigo = new Paragraph("Código");
							    codigo.getFont().setStyle(Font.BOLD);
							    codigo.getFont().setSize(7);
							    codigo.setAlignment(Element.ALIGN_CENTER);
							    
							    Paragraph ubicacion = new Paragraph("Ubicación");
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
							    
							    }
							    
								
							    Paragraph contenedor = new Paragraph();
							    
							    
							    
							    
							    String tareas=tabla.getValueAt(tabla.getSelectedRow(), 4).toString();
							    
							    String array_tareas[]= tareas.split("\n");
							    
							    List list_tareas = new List(List.UNORDERED);
							    
							   
							    
							    for(int i=0; i<array_tareas.length; i++) {
							    	
							    	list_tareas.add(array_tareas[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_tareas = new Paragraph("\n Tareas");			    		
							    		
							    //titulo_tareas.add(list_tareas);	
							    
							    
							    		
							    titulo_tareas.getFont().setStyle(Font.BOLD);
							    titulo_tareas.getFont().setSize(12);
							    titulo_tareas.setAlignment(Element.ALIGN_LEFT);
							    
							    
							    Paragraph lista_tareas = new Paragraph();
							    
							    lista_tareas.add(list_tareas);	
							    
							    lista_tareas.getFont().setStyle(Font.PLAIN);
							    lista_tareas.getFont().setSize(7);
							    lista_tareas.setAlignment(Element.ALIGN_LEFT);
							    
							    
							    
							    
							    String herramientas=tabla.getValueAt(tabla.getSelectedRow(), 5).toString();
							    
							    String array_herramientas[]= herramientas.split("\n");
							    
							    List list_herramientas = new List(List.UNORDERED);
							    
							    
							   
							    
							    for(int i=0; i<array_herramientas.length; i++) {
							    	
							    	list_herramientas.add(array_herramientas[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_herramientas = new Paragraph(" Herramientas");			    		
							    		
							    //titulo_herramientas.add(list_herramientas);		
							    		
							    titulo_herramientas.getFont().setStyle(Font.BOLD);
							    titulo_herramientas.getFont().setSize(12);
							    titulo_herramientas.setAlignment(Element.ALIGN_CENTER);
							    
							    
							    Paragraph lista_herramientas = new Paragraph();
							    
							    lista_herramientas.add(list_herramientas);	
							    
							    lista_herramientas.getFont().setStyle(Font.PLAIN);
							    lista_herramientas.getFont().setSize(7);
							    lista_herramientas.setAlignment(Element.ALIGN_LEFT);
							    
							    
							    
							    
							    String materiales=tabla.getValueAt(tabla.getSelectedRow(), 6).toString();
							    
							    String array_materiales[]= materiales.split("\n");
							    
							    List list_materiales = new List(List.UNORDERED);
							    
							   
							    
							    for(int i=0; i<array_materiales.length; i++) {
							    	
							    	list_materiales.add(array_materiales[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_materiales = new Paragraph(" Materiales");			    		
							    		
							    //titulo_materiales.add(list_materiales);		
							    		
							    titulo_materiales.getFont().setStyle(Font.BOLD);
							    titulo_materiales.getFont().setSize(12);
							    titulo_materiales.setAlignment(Element.ALIGN_CENTER);
							    
							    
							    Paragraph lista_materiales = new Paragraph();
							    
							    lista_materiales.add(list_materiales);	
							    
							    lista_materiales.getFont().setStyle(Font.PLAIN);
							    lista_materiales.getFont().setSize(7);
							    lista_materiales.setAlignment(Element.ALIGN_LEFT);
							    
							    
							    
							    contenedor.add(titulo_tareas);
							    contenedor.add(lista_tareas);
							    contenedor.add(titulo_herramientas);
							    contenedor.add(lista_herramientas);
							    contenedor.add(titulo_materiales);
							    contenedor.add(lista_materiales);
							    
								
									
								
								doc.add(titulo);								
								doc.add(table);
								doc.add(titulo_equipos);
								doc.add(table_equipos);
								doc.add(contenedor);
								
								
								doc.close();
								
							}
							
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
							
						} catch (DocumentException e1) {
							
							e1.printStackTrace();
							
						}
						
						JOptionPane.showMessageDialog(null, "Planes de Mantenimiento exportados con exito.", null, JOptionPane.INFORMATION_MESSAGE);
					 
					 
				 }
		
	    }
		
		
	}
		
	

}
