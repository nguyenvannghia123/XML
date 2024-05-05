package baitapXML;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class XML {

    public static void main(String[] args) {
        // Nhập đường dẫn thư mục từ người dùng
        String directoryPath = getInputDirectoryPath();

        // Kiểm tra và duyệt thư mục
        try {
            Path path = Paths.get(directoryPath);
            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xmlBuilder.append("<directory_tree>\n");

            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    xmlBuilder.append("  <").append(dir.getFileName()).append(">\n");
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    xmlBuilder.append("    <file>").append(file.getFileName()).append("</file>\n");
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    xmlBuilder.append("  </").append(dir.getFileName()).append(">\n");
                    return FileVisitResult.CONTINUE;
                }
            });

            xmlBuilder.append("</directory_tree>\n");

            // Ghi nội dung XML vào file
            String xmlContent = xmlBuilder.toString();
            writeXmlToFile(xmlContent, "directory_tree.xml");
            System.out.println("Đã tạo thành công file XML!");
        } catch (IOException e) {
            System.err.println("Lỗi khi duyệt thư mục và tạo file XML: " + e.getMessage());
        }
    }

    private static String getInputDirectoryPath() {
        // Lấy đường dẫn thư mục từ người dùng (ví dụ: "/path/to/directory")
        // Bạn có thể sử dụng Scanner để nhập từ bàn phím hoặc thay thế bằng đường dẫn cụ thể
        return "/path/to/directory";
    }

    private static void writeXmlToFile(String xmlContent, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(xmlContent);
        }
    }
}

