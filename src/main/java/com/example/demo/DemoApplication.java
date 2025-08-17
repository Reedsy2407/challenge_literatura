package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Autor;
import com.example.demo.model.Libro;
import com.example.demo.services.AutorServicio;
import com.example.demo.services.LibroServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(LibroServicio libroServicio, AutorServicio autorServicio) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean running = true;
			while (running) {
				System.out.println("\n--- LiterAlura ---");
				System.out.println("1) Buscar libro por título y guardar");
				System.out.println("2) Listar todos los libros");
				System.out.println("3) Listar autores");
				System.out.println("4) Listar autores vivos en un año");
				System.out.println("5) Listar libros por idioma");
				System.out.println("6) Cantidad de libros por idioma");
				System.out.println("0) Salir");
				System.out.print("Elige opción: ");
				String opt = scanner.nextLine().trim();
				try {
					switch (opt) {
						case "1":
							System.out.print("Título: ");
							String titulo = scanner.nextLine().trim();
							System.out.println(libroServicio.buscarYGuardarPorTitulo(titulo));
							break;
						case "2":
							List<Libro> libros = libroServicio.listarTodos();
							if (libros.isEmpty()) System.out.println("No hay libros guardados.");
							else libros.forEach(System.out::println);
							break;
						case "3":
							List<Autor> autores = autorServicio.listarTodos();
							if (autores.isEmpty()) System.out.println("No hay autores guardados.");
							else autores.forEach(System.out::println);
							break;
						case "4":
							System.out.print("Año: ");
							int anio = Integer.parseInt(scanner.nextLine().trim());
							List<Autor> vivos = autorServicio.buscarVivosEnAnio(anio);
							if (vivos.isEmpty()) System.out.println("No se encontraron autores vivos en " + anio);
							else vivos.forEach(System.out::println);
							break;
						case "5":
							System.out.print("Idioma (ej: en, es): ");
							String idioma = scanner.nextLine().trim();
							List<Libro> porIdioma = libroServicio.buscarPorIdioma(idioma);
							if (porIdioma.isEmpty()) System.out.println("No existen libros en " + idioma);
							else porIdioma.forEach(System.out::println);
							break;
						case "6":
							System.out.print("Idioma para contar: ");
							String idiomaContar = scanner.nextLine().trim();
							long count = libroServicio.contarPorIdioma(idiomaContar);
							System.out.println("Cantidad de libros en '" + idiomaContar + "': " + count);
							break;
						case "0":
							running = false;
							break;
						default:
							System.out.println("Opción no válida.");
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			System.out.println("Saliendo de LiterAlura. ¡Hasta luego!");
		};
	}

}
