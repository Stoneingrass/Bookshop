package com.example.bookshop.helpers;

import com.example.bookshop.entity.Book;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@NoArgsConstructor
public final class CSVManager {
    public static void save(List<Book> data, HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=books.csv");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("id,name,genre\n");

            for (Book book : data) {
                String line = String.format("%d,%s,%s\n", book.getId(), book.getName(), book.getGenre().toString());
                writer.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
