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
import vista.Visualizar_Plan_Mantenimiento;

public class Evento_Exportar_Orden_Trabajo implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		
		
		File archivo;
		
		JFileChooser nuevo=new JFileChooser("src/Documentos Exportados/Orden de Trabajo");	
		
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
							
							JTable tabla=Visualizar_Orden_Trabajo.tabla;
										
							PdfPTable table = new PdfPTable(9);			
										
							int num_filas=tabla.getRowCount();
									
							tabla.setRowHeight(35);
							
							table.setWidthPercentage(100);
							
							table.setWidths(new float[] {15,30,30,40,45,45,45,40,30});
							
							Paragraph titulo = new Paragraph("Ordenes de Trabajo \n\n");
							titulo.getFont().setStyle(Font.BOLD);
							titulo.getFont().setSize(15);
							titulo.setAlignment(Element.ALIGN_CENTER);
							
							Paragraph id = new Paragraph("ID");
							id.getFont().setStyle(Font.BOLD);
							id.getFont().setSize(7);
							id.setAlignment(Element.ALIGN_CENTER);
							
							Paragraph plan_mantenimiento = new Paragraph("Plan Mtto.");
							plan_mantenimiento.getFont().setStyle(Font.BOLD);
							plan_mantenimiento.getFont().setSize(7);
							plan_mantenimiento.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tipo_mantenimiento = new Paragraph("Tipo Mtto.");
						    tipo_mantenimiento.getFont().setStyle(Font.BOLD);
						    tipo_mantenimiento.getFont().setSize(7);
						    tipo_mantenimiento.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph fecha_inicio = new Paragraph("Fecha inicio");
						    fecha_inicio.getFont().setStyle(Font.BOLD);
						    fecha_inicio.getFont().setSize(7);
						    fecha_inicio.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph fecha_terminado = new Paragraph("Fecha Terminado");
						    fecha_terminado.getFont().setStyle(Font.BOLD);
						    fecha_terminado.getFont().setSize(7);
						    fecha_terminado.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tiempo_taller = new Paragraph("Tiempo Taller");
						    tiempo_taller.getFont().setStyle(Font.BOLD);
						    tiempo_taller.getFont().setSize(7);
						    tiempo_taller.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tiempo_preparacion = new Paragraph("Tiempo Prep.");
						    tiempo_preparacion.getFont().setStyle(Font.BOLD);
						    tiempo_preparacion.getFont().setSize(7);
						    tiempo_preparacion.setAlignment(Element.ALIGN_CENTER);		    
						    
						    Paragraph tipo_tarea = new Paragraph("Tipo Tarea");
						    tipo_tarea.getFont().setStyle(Font.BOLD);
						    tipo_tarea.getFont().setSize(7);
						    tipo_tarea.setAlignment(Element.ALIGN_CENTER);
						    
						    Paragraph tarea_realizada = new Paragraph("Realizado");
						    tarea_realizada.getFont().setStyle(Font.BOLD);
						    tarea_realizada.getFont().setSize(7);
						    tarea_realizada.setAlignment(Element.ALIGN_CENTER);
						    
							
							
							
							
							Stream.of(id, plan_mantenimiento, tipo_mantenimiento, fecha_inicio, fecha_terminado, tiempo_taller, tiempo_preparacion, tipo_tarea, tarea_realizada).forEach(ct -> {
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
								
								/*for (int i = 0; i < seleccionadas.length; i++) {					
									
									

									
									Paragraph columna1 = new Paragraph(tabla.getValueAt(seleccionadas[i], 0).toString());
								    columna1.getFont().setStyle(Font.BOLD);
								    columna1.getFont().setSize(5);
								    columna1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna2 = new Paragraph(tabla.getValueAt(seleccionadas[i], 2).toString());
								    columna2.getFont().setStyle(Font.BOLD);
								    columna2.getFont().setSize(5);
								    columna2.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna3 = new Paragraph(tabla.getValueAt(seleccionadas[i], 3).toString());
								    columna3.getFont().setStyle(Font.BOLD);
								    columna3.getFont().setSize(5);
								    columna3.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna4 = new Paragraph(tabla.getValueAt(seleccionadas[i], 4).toString());
								    columna4.getFont().setStyle(Font.BOLD);
								    columna4.getFont().setSize(5);
								    columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna5 = new Paragraph(tabla.getValueAt(seleccionadas[i], 5).toString());
								    columna5.getFont().setStyle(Font.BOLD);
								    columna5.getFont().setSize(5);
								    columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna6 = new Paragraph(tabla.getValueAt(seleccionadas[i], 6).toString());
								    columna6.getFont().setStyle(Font.BOLD);
								    columna6.getFont().setSize(5);
								    columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna7 = new Paragraph(tabla.getValueAt(seleccionadas[i], 7).toString());
								    columna7.getFont().setStyle(Font.BOLD);
								    columna7.getFont().setSize(5);
								    columna7.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna8 = new Paragraph(tabla.getValueAt(seleccionadas[i], 11).toString());
								    columna8.getFont().setStyle(Font.BOLD);
								    columna8.getFont().setSize(5);
								    columna8.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna9 = new Paragraph(tabla.getValueAt(seleccionadas[i], 14).toString());
								    columna9.getFont().setStyle(Font.BOLD);
								    columna9.getFont().setSize(5);
								    columna9.setAlignment(Element.ALIGN_CENTER);
								    
								    
								       
								    
								   
								   				
									
									table.addCell(columna1);
									table.addCell(columna2);
									table.addCell(columna3);
									table.addCell(columna4);
									table.addCell(columna5);
									table.addCell(columna6);
									table.addCell(columna7);
									table.addCell(columna8);
									table.addCell(columna9);
									
									
									System.out.println("primer condicional");
									
									
								}*/
								
								System.out.println("primer condicional");
								
							}else {
								
								for (int i = 0; i < tabla.getRowCount(); i++) {

									
									Paragraph columna1 = new Paragraph(tabla.getValueAt(i, 0).toString());
								    columna1.getFont().setStyle(Font.BOLD);
								    columna1.getFont().setSize(5);
								    columna1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna2 = new Paragraph(tabla.getValueAt(i, 2).toString());
								    columna2.getFont().setStyle(Font.BOLD);
								    columna2.getFont().setSize(5);
								    columna2.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna3 = new Paragraph(tabla.getValueAt(i, 3).toString());
								    columna3.getFont().setStyle(Font.BOLD);
								    columna3.getFont().setSize(5);
								    columna3.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna4 = new Paragraph(tabla.getValueAt(i, 4).toString());
								    columna4.getFont().setStyle(Font.BOLD);
								    columna4.getFont().setSize(5);
								    columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna5 = new Paragraph(tabla.getValueAt(i, 5).toString());
								    columna5.getFont().setStyle(Font.BOLD);
								    columna5.getFont().setSize(5);
								    columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna6 = new Paragraph(tabla.getValueAt(i, 6).toString());
								    columna6.getFont().setStyle(Font.BOLD);
								    columna6.getFont().setSize(5);
								    columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna7 = new Paragraph(tabla.getValueAt(i, 7).toString());
								    columna7.getFont().setStyle(Font.BOLD);
								    columna7.getFont().setSize(5);
								    columna7.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna8 = new Paragraph(tabla.getValueAt(i, 11).toString());
								    columna8.getFont().setStyle(Font.BOLD);
								    columna8.getFont().setSize(5);
								    columna8.setAlignment(Element.ALIGN_CENTER);
								    
								    
								    Paragraph columna9 = new Paragraph(tabla.getValueAt(i, 14).toString());
								    columna9.getFont().setStyle(Font.BOLD);
								    columna9.getFont().setSize(5);
								    columna9.setAlignment(Element.ALIGN_CENTER); 
								    
								   
								   				
									
									table.addCell(columna1);
									table.addCell(columna2);
									table.addCell(columna3);
									table.addCell(columna4);
									table.addCell(columna5);
									table.addCell(columna6);
									table.addCell(columna7);
									table.addCell(columna8);
									table.addCell(columna9);
									
									System.out.println("segundo condicional");
									
									
								}
								
							}
							
							
							
							
							if(num_filas>1 && tabla.getSelectedRows().length!=1) {				
								
								doc.add(titulo);
								doc.add(table);
								doc.close();
								
								System.out.println("tercer condicional");
								
							}else if(tabla.getSelectedRows().length==1 || num_filas<=1){	
								
								
								table.resetColumnCount(9);
								
								table.setWidths(new float[] {15,35,55,40,60,50,40,30,30});
								
								System.out.println("cuarto condicional");
								
								
								/*Stream.of(id, plan_mantenimiento, tipo_mantenimiento, fecha_inicio, fecha_terminado, tiempo_taller, tiempo_preparacion, tipo_tarea, tarea_realizada).forEach(ct -> {
									PdfPCell header = new PdfPCell();
									header.setBackgroundColor(BaseColor.LIGHT_GRAY);						
									header.setBorderWidth(2);
									header.setHorizontalAlignment(Element.ALIGN_CENTER);
									header.setFixedHeight(17);
									header.setPhrase(new Phrase(ct));						
									table.addCell(header);
									
									
								});*/
								
								

									
								Paragraph columna1 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
								columna1.getFont().setStyle(Font.BOLD);
								columna1.getFont().setSize(5);
								columna1.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna2 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
								columna2.getFont().setStyle(Font.BOLD);
								columna2.getFont().setSize(5);
								columna2.setAlignment(Element.ALIGN_CENTER);
								    				    	    
								    
								    
								Paragraph columna4 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
								columna4.getFont().setStyle(Font.BOLD);
								columna4.getFont().setSize(5);
								columna4.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna5 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 4).toString());
								columna5.getFont().setStyle(Font.BOLD);
								columna5.getFont().setSize(5);
								columna5.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna6 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 5).toString());
								columna6.getFont().setStyle(Font.BOLD);
								columna6.getFont().setSize(5);
								columna6.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna7 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 6).toString());
								columna7.getFont().setStyle(Font.BOLD);
								columna7.getFont().setSize(5);
								columna7.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna8 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 7).toString());
								columna8.getFont().setStyle(Font.BOLD);
								columna8.getFont().setSize(5);
								columna8.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna9 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 11).toString());
								columna9.getFont().setStyle(Font.BOLD);
								columna9.getFont().setSize(5);
								columna9.setAlignment(Element.ALIGN_CENTER);
								    
								    
								Paragraph columna10 = new Paragraph(tabla.getValueAt(tabla.getSelectedRow(), 14).toString());
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
								
								
								
								
								
								
								
								
								//---------------------------------------------------------Equipos------------------------------------------------------------------------------
								
								
								
								Paragraph titulo_equipos = new Paragraph("\n Equipos"+"\n\n");
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
							    
							    }
							    
							    
							    
							    
							    
							    //---------------------------------------------------------Empleados-------------------------------------------------------------
							    
							    
							    
							    
							    Paragraph titulo_empleados = new Paragraph("\n Empleados"+"\n\n");
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
							    
							    }
							    
							    
							    
							    
							    
							    //----------------------------------------------***************************************--------------------------------------------
							    
								
							    Paragraph contenedor = new Paragraph();
							    
							    
							    
							    
							    String tareas=tabla.getValueAt(tabla.getSelectedRow(), 8).toString();
							    
							    String array_tareas[]= tareas.split("\n");
							    
							    List list_tareas = new List(List.UNORDERED);
							    
							   
							    
							    for(int i=0; i<array_tareas.length; i++) {
							    	
							    	list_tareas.add(array_tareas[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_tareas = new Paragraph("\n Tareas:");			    		
							    		
							    titulo_tareas.add(list_tareas);		
							    		
							    titulo_tareas.getFont().setStyle(Font.BOLD);
							    titulo_tareas.getFont().setSize(12);
							    titulo_tareas.setAlignment(Element.ALIGN_LEFT);
							    
							    
							    
							    
							    
							    String herramientas=tabla.getValueAt(tabla.getSelectedRow(), 9).toString();
							    
							    String array_herramientas[]= herramientas.split("\n");
							    
							    List list_herramientas = new List(List.UNORDERED);
							    
							   
							    
							    for(int i=0; i<array_herramientas.length; i++) {
							    	
							    	list_herramientas.add(array_herramientas[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_herramientas = new Paragraph("Herramientas:");			    		
							    		
							    titulo_herramientas.add(list_herramientas);		
							    		
							    titulo_herramientas.getFont().setStyle(Font.BOLD);
							    titulo_herramientas.getFont().setSize(12);
							    titulo_herramientas.setAlignment(Element.ALIGN_CENTER);
							    
							    
							    
							    
							    String materiales=tabla.getValueAt(tabla.getSelectedRow(), 10).toString();
							    
							    String array_materiales[]= materiales.split("\n");
							    
							    List list_materiales = new List(List.UNORDERED);
							    
							   
							    
							    for(int i=0; i<array_materiales.length; i++) {
							    	
							    	list_materiales.add(array_materiales[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_materiales = new Paragraph("Materiales:");			    		
							    		
							    titulo_materiales.add(list_materiales);		
							    		
							    titulo_materiales.getFont().setStyle(Font.BOLD);
							    titulo_materiales.getFont().setSize(12);
							    titulo_materiales.setAlignment(Element.ALIGN_CENTER);
							    
							    
							    
							    
							    String observaciones=tabla.getValueAt(tabla.getSelectedRow(), 12).toString();
							    
							    String array_observaciones[]= observaciones.split("\n");
							    
							    List list_observaciones = new List(List.UNORDERED);
							    
							   
							    
							    for(int i=0; i<array_observaciones.length; i++) {
							    	
							    	list_observaciones.add(array_observaciones[i]);   	
							    	
							    	
							    }			    
								
							    Paragraph titulo_observaciones = new Paragraph("Observaciones:");			    		
							    		
							    titulo_observaciones.add(list_observaciones);		
							    		
							    titulo_observaciones.getFont().setStyle(Font.BOLD);
							    titulo_observaciones.getFont().setSize(10);
							    titulo_observaciones.setAlignment(Element.ALIGN_CENTER);
							    
							    
							    
							    contenedor.add(titulo_tareas);
							    contenedor.add(titulo_herramientas);
							    contenedor.add(titulo_materiales);
							    contenedor.add(titulo_observaciones);
							    
								
									
								
								doc.add(titulo);								
								doc.add(table);
								doc.add(titulo_equipos);
								doc.add(table_equipos);
								doc.add(titulo_empleados);
								doc.add(table_empleados);
								doc.add(contenedor);
								
								
								doc.close();
								
							}
							
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
							
						} catch (DocumentException e1) {
							
							e1.printStackTrace();
							
						} catch (Exception e1) {
							
							JOptionPane.showMessageDialog(null, "Seleccione una Orden de Trabajo para exportar.");
							
							return;
							
						}
						
						JOptionPane.showMessageDialog(null, "Ordenes de Trabajo exportadas con exito.", null, JOptionPane.INFORMATION_MESSAGE);
					 
				 }
		
	    }
	    
	    
		
		
	}

}
