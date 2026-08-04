"""
Generate: Spring Boot Product Service – Project Overview Guide (PDF)
"""
from datetime import datetime
from pathlib import Path

from reportlab.lib import colors
from reportlab.lib.enums import TA_CENTER, TA_JUSTIFY
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle, getSampleStyleSheet
from reportlab.lib.units import cm
from reportlab.platypus import HRFlowable, PageBreak, Paragraph, SimpleDocTemplate, Spacer, Table, TableStyle

PROJECT = Path(__file__).resolve().parent
OUTPUT = PROJECT / "Spring_Boot_Product_Service_Learning_Guide.pdf"

BLUE = colors.HexColor("#1565C0")
NAVY = colors.HexColor("#0D1B2A")
TEAL = colors.HexColor("#00897B")
GREEN_BG = colors.HexColor("#E8F5E9")
ORANGE_BG = colors.HexColor("#FFF3E0")
RED_BG = colors.HexColor("#FFEBEE")
PURPLE_BG = colors.HexColor("#F3E5F5")
GREY_BG = colors.HexColor("#ECEFF1")
WHITE = colors.white
TEXT = colors.HexColor("#212121")
MUTED = colors.HexColor("#546E7A")


def styles():
    b = getSampleStyleSheet()
    return {
        "cover": ParagraphStyle("cover", parent=b["Title"], fontSize=22, leading=28, textColor=WHITE, alignment=TA_CENTER, fontName="Helvetica-Bold"),
        "ch": ParagraphStyle("ch", parent=b["Heading1"], fontSize=16, textColor=BLUE, spaceBefore=12, spaceAfter=8, fontName="Helvetica-Bold"),
        "sec": ParagraphStyle("sec", parent=b["Heading2"], fontSize=12, textColor=TEAL, spaceBefore=8, spaceAfter=5, fontName="Helvetica-Bold"),
        "body": ParagraphStyle("body", parent=b["Normal"], fontSize=10, leading=14, textColor=TEXT, alignment=TA_JUSTIFY, spaceAfter=6),
        "bullet": ParagraphStyle("bullet", parent=b["Normal"], fontSize=10, leading=13, leftIndent=14, spaceAfter=3),
    }


def box(text, bg, border=TEAL, width=16.8):
    p = Paragraph(text, ParagraphStyle("bx", fontSize=9.5, leading=13, textColor=TEXT))
    t = Table([[p]], colWidths=[width * cm])
    t.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, -1), bg),
        ("BOX", (0, 0), (-1, -1), 1, border),
        ("LEFTPADDING", (0, 0), (-1, -1), 10),
        ("RIGHTPADDING", (0, 0), (-1, -1), 10),
        ("TOPPADDING", (0, 0), (-1, -1), 8),
        ("BOTTOMPADDING", (0, 0), (-1, -1), 8),
    ]))
    return t


def info(t): return box(f"<b>INFO</b><br/>{t}", GREEN_BG, TEAL)
def warn(t): return box(f"<b>NOTE</b><br/>{t}", ORANGE_BG, colors.HexColor("#EF6C00"))
def mistake(t): return box(f"<b>ISSUE</b><br/>{t}", RED_BG, colors.HexColor("#C62828"))


def table(headers, rows, col_widths=None):
    data = [headers] + rows
    t = Table(data, colWidths=col_widths)
    t.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, 0), BLUE),
        ("TEXTCOLOR", (0, 0), (-1, 0), WHITE),
        ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
        ("FONTSIZE", (0, 0), (-1, -1), 8.5),
        ("GRID", (0, 0), (-1, -1), 0.5, colors.grey),
        ("VALIGN", (0, 0), (-1, -1), "TOP"),
        ("ROWBACKGROUNDS", (0, 1), (-1, -1), [WHITE, GREY_BG]),
        ("LEFTPADDING", (0, 0), (-1, -1), 6),
        ("RIGHTPADDING", (0, 0), (-1, -1), 6),
    ]))
    return t


def header_footer(canvas, doc):
    canvas.saveState()
    canvas.setFillColor(NAVY)
    canvas.rect(0, A4[1] - 1.0 * cm, A4[0], 1.0 * cm, fill=1, stroke=0)
    canvas.setFillColor(WHITE)
    canvas.setFont("Helvetica-Bold", 8)
    canvas.drawString(1.5 * cm, A4[1] - 0.72 * cm, "Spring Boot Product Service — Project Overview")
    canvas.setFillColor(MUTED)
    canvas.setFont("Helvetica", 7)
    canvas.drawRightString(A4[0] - 1.5 * cm, 0.9 * cm, f"Page {canvas.getPageNumber()}")
    canvas.restoreState()


def build():
    st = styles()
    doc = SimpleDocTemplate(str(OUTPUT), pagesize=A4, rightMargin=1.8*cm, leftMargin=1.8*cm, topMargin=1.8*cm, bottomMargin=1.8*cm)
    story = []

    # COVER
    banner = Table([[Paragraph("Spring Boot Product Service<br/><font size='10' color='#80CBC4'>Complete Project Overview Guide</font>", st["cover"])]], colWidths=[16.8*cm], rowHeights=[4.5*cm])
    banner.setStyle(TableStyle([("BACKGROUND", (0,0), (-1,-1), NAVY), ("VALIGN", (0,0), (-1,-1), "MIDDLE"), ("ALIGN", (0,0), (-1,-1), "CENTER")]))
    story += [
        Spacer(1, 1.5*cm), banner, Spacer(1, 0.4*cm),
        Paragraph("<b>Detailed overview of your entire Spring Boot project</b>", ParagraphStyle("s", fontSize=11, alignment=TA_CENTER, textColor=NAVY)),
        Spacer(1, 0.2*cm),
        Paragraph(f"Generated: {datetime.now().strftime('%B %d, %Y')} | Java 17 | Spring Boot 4.1", ParagraphStyle("d", fontSize=9, alignment=TA_CENTER, textColor=MUTED)),
        PageBreak(),
    ]

    # 1. WHAT IS THIS PROJECT
    story.append(Paragraph("1. What Is This Project?", st["ch"]))
    story.append(Paragraph(
        "Your <b>productService</b> is a <b>Spring Boot REST API</b> that acts as a <b>middle layer (proxy)</b> "
        "between a client (Postman, browser, frontend) and the external <b>FakeStore API</b> (fakestoreapi.com). "
        "It does <b>not</b> use a database. Every product/category request is forwarded to FakeStore, the JSON "
        "response is converted into your own Java objects, and sent back to the caller.",
        st["body"]))
    story.append(info(
        "Think of it like a translator: FakeStore speaks one JSON format, your app converts it into your own "
        "<b>Product</b> and <b>Category</b> models before returning data to the user."))
    story.append(Spacer(1, 0.2*cm))

    # 2. TECH STACK
    story.append(Paragraph("2. Technology Stack", st["ch"]))
    story.append(table(
        ["Technology", "Purpose in Your Project"],
        [
            ["Java 17", "Programming language"],
            ["Spring Boot 4.1", "Framework — auto-configures Tomcat, Jackson, DI"],
            ["Spring Web MVC", "REST controllers, HTTP handling"],
            ["RestClient", "Modern HTTP client to call FakeStore API"],
            ["Lombok", "Auto-generates getters/setters (@Getter, @Setter)"],
            ["Jackson", "Converts JSON ↔ Java objects automatically"],
            ["Embedded Tomcat", "Runs server on port 8080 (default)"],
            ["Maven (pom.xml)", "Dependency management and build tool"],
        ],
        [4*cm, 12.8*cm],
    ))
    story.append(PageBreak())

    # 3. PROJECT STRUCTURE
    story.append(Paragraph("3. Project Structure", st["ch"]))
    story.append(Paragraph("Your code is organized into clear layers under <b>org.pavan.productservice</b>:", st["body"]))
    story.append(box(
        "<b>productService/</b><br/>"
        "├── pom.xml &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → Dependencies &amp; build config<br/>"
        "├── application.properties &nbsp;&nbsp; → App name (port 8080 default)<br/>"
        "└── src/main/java/org/pavan/productservice/<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;├── ProductServiceApplication.java &nbsp; → Main entry point<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;├── config/RestClientConfig.java &nbsp;&nbsp;&nbsp;&nbsp; → RestClient bean setup<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;├── controllers/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → REST API endpoints<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; ├── ProductController.java<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; ├── CategoryController.java<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; └── ExceptionAdvices.java<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;├── services/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → Business logic layer<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; ├── ProductService.java (interface)<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; ├── FakeStoreProductImplementation.java<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; ├── CategoryService.java (interface)<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; └── FakeStoreCategoryImplementation.java<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;├── clients/fakestoreapi/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → HTTP calls to FakeStore<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; ├── FakeStoreProductClient.java<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp; └── FakeStoreCategoryClient.java<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;├── dtos/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → JSON shape from FakeStore<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;├── models/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → Your internal domain objects<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;└── exceptions/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → Custom error handling",
        GREY_BG, BLUE))
    story.append(PageBreak())

    # 4. ARCHITECTURE
    story.append(Paragraph("4. Architecture — How Layers Connect", st["ch"]))
    story.append(box(
        "<b>Layered Architecture:</b><br/><br/>"
        "<font color='#1565C0'><b>Client (Postman/Browser)</b></font><br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;↓ HTTP Request (GET /products/5)<br/>"
        "<font color='#00897B'><b>Controller Layer</b></font> — ProductController, CategoryController<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;↓ Calls service interface<br/>"
        "<font color='#00897B'><b>Service Layer</b></font> — FakeStoreProductImplementation<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;↓ Converts DTO ↔ Model, calls client<br/>"
        "<font color='#00897B'><b>Client Layer</b></font> — FakeStoreProductClient<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;↓ Uses RestClient<br/>"
        "<font color='#6A1B9A'><b>RestClient</b></font> — HTTP call to fakestoreapi.com<br/>"
        "&nbsp;&nbsp;&nbsp;&nbsp;↓ JSON response<br/>"
        "<font color='#EF6C00'><b>Jackson</b></font> — JSON → ProductDto → Product → JSON back to client",
        GREY_BG, TEAL))
    story.append(Spacer(1, 0.3*cm))
    story.append(Paragraph("<b>Why this structure?</b>", st["sec"]))
    for item in [
        "<b>Controller</b> — Only handles HTTP (URLs, status codes, request/response). No business logic.",
        "<b>Service</b> — Business rules and DTO-to-Model conversion. Controller doesn't know about FakeStore.",
        "<b>Client</b> — Only HTTP communication. Service doesn't know URLs or RestClient details.",
        "<b>Config</b> — RestClient base URL defined once, shared by all clients.",
    ]:
        story.append(Paragraph(f"• {item}", st["bullet"]))
    story.append(PageBreak())

    # 5. REQUEST FLOW
    story.append(Paragraph("5. Complete Request Flow (Example)", st["ch"]))
    story.append(Paragraph("<b>GET /products/5 — step by step:</b>", st["sec"]))
    steps = [
        ("1", "User sends", "GET http://localhost:8080/products/5"),
        ("2", "Tomcat", "Embedded server receives the HTTP request on port 8080"),
        ("3", "DispatcherServlet", "Spring's front controller routes the request"),
        ("4", "HandlerMapping", "Finds ProductController.getSingleProduct(5)"),
        ("5", "Controller", "Calls productService.getSingleProduct(5)"),
        ("6", "Service", "Calls fakeStoreProductClient.getSingleProduct(5)"),
        ("7", "RestClient", "Sends GET https://fakestoreapi.com/products/5"),
        ("8", "FakeStore", "Returns JSON: { id, title, price, image, category, ... }"),
        ("9", "Jackson", "Converts JSON → ProductDto"),
        ("10", "Service", "Converts ProductDto → Product (your model)"),
        ("11", "Controller", "Wraps Product in ResponseEntity, returns 200 OK + JSON"),
        ("12", "Client", "Receives JSON product in Postman/browser"),
    ]
    story.append(table(["Step", "Component", "What Happens"], steps, [1.2*cm, 3*cm, 12.6*cm]))
    story.append(Spacer(1, 0.3*cm))
    story.append(warn(
        "If product ID 999 doesn't exist: FakeStore returns 404. RestClient throws an exception. "
        "Your app has no handler for that, so the user sees <b>500 Internal Server Error</b> instead of 404."))
    story.append(PageBreak())

    # 6. API ENDPOINTS
    story.append(Paragraph("6. Your API Endpoints", st["ch"]))
    story.append(Paragraph("<b>Product Endpoints (/products)</b>", st["sec"]))
    story.append(table(
        ["Method", "URL", "What It Does", "Status"],
        [
            ["GET", "/products", "Get all products", "Working"],
            ["GET", "/products/{id}", "Get one product by ID", "Working"],
            ["POST", "/products", "Create new product", "Working (returns 200, should be 201)"],
            ["PATCH", "/products/{id}", "Partial update", "Broken — FakeStore doesn't support PATCH"],
            ["PUT", "/products/{id}", "Full replace", "Bug — wrong field mapping in controller"],
            ["DELETE", "/products/{id}", "Delete product", "Working"],
        ],
        [1.5*cm, 4*cm, 7.3*cm, 4*cm],
    ))
    story.append(Spacer(1, 0.3*cm))
    story.append(Paragraph("<b>Category Endpoints (/categories)</b>", st["sec"]))
    story.append(table(
        ["Method", "URL", "What It Does", "Status"],
        [
            ["GET", "/categories", "Get all category names", "Working"],
            ["GET", "/categories/{id}/products", "Products in category", "Broken — wrong FakeStore URL"],
            ["GET", "/categories/{id}", "Get category by ID", "Stub only (returns string)"],
            ["POST", "/categories", "Add category", "Stub only"],
            ["PUT", "/categories/{id}", "Update category", "Stub only"],
            ["DELETE", "/categories/{id}", "Delete category", "Stub only"],
        ],
        [1.5*cm, 4.5*cm, 6.8*cm, 4*cm],
    ))
    story.append(PageBreak())

    # 7. KEY FILES OVERVIEW
    story.append(Paragraph("7. Key Files — What Each One Does", st["ch"]))
    files = [
        ("ProductServiceApplication.java", "Main class. @SpringBootApplication starts Spring, scans all packages, launches Tomcat on port 8080."),
        ("RestClientConfig.java", "Creates a shared RestClient bean with base URL https://fakestoreapi.com. All clients use this single instance."),
        ("ProductController.java", "Exposes /products REST API. Receives HTTP requests, calls ProductService, returns Product JSON."),
        ("CategoryController.java", "Exposes /categories REST API. GET endpoints work; POST/PUT/DELETE are placeholder stubs."),
        ("FakeStoreProductImplementation.java", "Implements ProductService. Calls FakeStoreProductClient and converts ProductDto → Product."),
        ("FakeStoreCategoryImplementation.java", "Implements CategoryService. Delegates to FakeStoreCategoryClient."),
        ("FakeStoreProductClient.java", "Makes actual HTTP calls: GET/POST/PUT/PATCH/DELETE to FakeStore /products endpoints using RestClient."),
        ("FakeStoreCategoryClient.java", "HTTP calls for categories. getAllCategories works. getProductsInCategory uses wrong URL."),
        ("ProductService.java", "Interface defining product operations. Allows swapping FakeStore for a database later."),
        ("CategoryService.java", "Interface for category operations."),
        ("ProductDto.java", "Matches FakeStore JSON: id, title, price, description, image, category, rating."),
        ("CategoryDto.java", "Simple object with category name."),
        ("Product.java", "Your domain model: title, price, description, Category object, imageUrl."),
        ("Category.java", "Domain category with name, description, products list."),
        ("BaseModel.java", "Shared fields: id, createdAt, lastUpdatedAt, isDeleted (for future database use)."),
        ("ExceptionAdvices.java", "Global exception handler. Catches NotFoundException → returns 404 JSON."),
        ("NotFoundException.java", "Custom exception thrown when product is not found."),
        ("ErrorResponseDto.java", "Error JSON body: { errorMessage: \"...\" } returned on 404."),
        ("pom.xml", "Maven config: Spring Boot 4.1, webmvc, lombok, actuator dependencies."),
        ("application.properties", "Only sets spring.application.name=productService."),
    ]
    story.append(table(["File", "Purpose"], files, [5.5*cm, 11.3*cm]))
    story.append(PageBreak())

    # 8. REST CLIENT
    story.append(Paragraph("8. RestClient — How Your Project Calls FakeStore", st["ch"]))
    story.append(Paragraph(
        "<b>RestClient</b> is Spring's modern HTTP client (Spring 6+). You configured it once in RestClientConfig "
        "with base URL <b>https://fakestoreapi.com</b>. Every call in your clients uses a fluent API:",
        st["body"]))
    story.append(box(
        "<b>Pattern used in your project:</b><br/><br/>"
        "<code>restClient.get()</code> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → Start a GET request<br/>"
        "<code>&nbsp;&nbsp;.uri(\"/products/{id}\", id)</code> → Set the URL path<br/>"
        "<code>&nbsp;&nbsp;.retrieve()</code> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; → Send the request<br/>"
        "<code>&nbsp;&nbsp;.body(ProductDto.class)</code> → Convert JSON response to Java object<br/><br/>"
        "<b>All calls in FakeStoreProductClient:</b><br/>"
        "• GET /products → get all products<br/>"
        "• GET /products/{id} → get single product<br/>"
        "• POST /products → create product<br/>"
        "• PUT /products/{id} → replace product<br/>"
        "• PATCH /products/{id} → partial update (FakeStore doesn't support this)<br/>"
        "• DELETE /products/{id} → delete product",
        GREY_BG, TEAL))
    story.append(Spacer(1, 0.3*cm))
    story.append(Paragraph("<b>RestClient vs RestTemplate (quick comparison)</b>", st["sec"]))
    story.append(table(
        ["", "RestClient (You Use)", "RestTemplate (Older)"],
        [
            ["Status", "Modern, recommended", "Legacy, maintenance mode"],
            ["Style", "Fluent: .get().uri().retrieve().body()", "Simple: getForObject(url, Class)"],
            ["Blocking", "Yes (synchronous)", "Yes (synchronous)"],
            ["Example", "restClient.get().uri(\"/products\").retrieve().body(ProductDto[].class)", "restTemplate.getForObject(\"/products\", ProductDto[].class)"],
        ],
        [3*cm, 6.9*cm, 6.9*cm],
    ))
    story.append(info("Use RestClient for new projects. RestTemplate still works but Spring recommends RestClient or WebClient for new code."))
    story.append(PageBreak())

    # 9. DTO vs MODEL
    story.append(Paragraph("9. DTO vs Model — Why Two Types of Objects?", st["ch"]))
    story.append(Paragraph(
        "Your project uses two types of Java objects for the same data. This is a standard industry pattern:",
        st["body"]))
    story.append(table(
        ["", "DTO (ProductDto)", "Model (Product)"],
        [
            ["Purpose", "Shape of FakeStore JSON", "Your internal business object"],
            ["Category field", "String (\"electronics\")", "Category object with name, description"],
            ["Image field", "image", "imageUrl"],
            ["Where used", "Client layer (HTTP boundary)", "Controller response, service layer"],
            ["Who creates", "Jackson auto-deserializes JSON", "Service converts from DTO"],
        ],
        [3*cm, 6.9*cm, 6.9*cm],
    ))
    story.append(Spacer(1, 0.2*cm))
    story.append(Paragraph(
        "<b>Conversion flow:</b> FakeStore JSON → ProductDto (automatic by Jackson) → Product (manual in "
        "FakeStoreProductImplementation.convertProductDtoToProduct) → returned to client as JSON.",
        st["body"]))
    story.append(PageBreak())

    # 10. EXCEPTION HANDLING
    story.append(Paragraph("10. Exception Handling Overview", st["ch"]))
    story.append(Paragraph("Your project has basic exception handling:", st["body"]))
    story.append(box(
        "<b>Current flow:</b><br/>"
        "1. ProductController checks if Optional is empty<br/>"
        "2. Throws <b>NotFoundException</b> with message<br/>"
        "3. <b>ExceptionAdvices</b> (@ControllerAdvice) catches it<br/>"
        "4. Creates <b>ErrorResponseDto</b> with error message<br/>"
        "5. Returns <b>404 Not Found</b> + JSON error body<br/><br/>"
        "<b>What's missing:</b><br/>"
        "• No handler for RestClient errors (FakeStore 404 → your 500)<br/>"
        "• No validation errors handler (400 Bad Request)<br/>"
        "• No generic 500 handler<br/>"
        "• NotFoundException is checked (should be RuntimeException)",
        GREY_BG, colors.HexColor("#6A1B9A")))
    story.append(Spacer(1, 0.2*cm))
    story.append(Paragraph("<b>HTTP Status Codes in Your Project:</b>", st["sec"]))
    story.append(table(
        ["Code", "Meaning", "When It Happens"],
        [
            ["200 OK", "Success", "GET products, POST product (should be 201)"],
            ["404 Not Found", "Resource missing", "NotFoundException from your code"],
            ["500 Internal Server Error", "Server crash", "RestClient failure, unhandled exceptions"],
        ],
        [2.5*cm, 4*cm, 10.3*cm],
    ))
    story.append(PageBreak())

    # 11. SPRING CONCEPTS USED
    story.append(Paragraph("11. Spring Boot Concepts Used in Your Project", st["ch"]))
    concepts = [
        ("@SpringBootApplication", "Starts the app, enables auto-configuration and component scanning."),
        ("@RestController", "Marks a class as REST API controller. Return values become JSON responses."),
        ("@RequestMapping", "Base URL prefix. '/products' means all methods start with /products."),
        ("@GetMapping / @PostMapping / etc.", "Maps specific HTTP method + path to a Java method."),
        ("@PathVariable", "Reads value from URL. /products/{productId} → productId = 5."),
        ("@RequestBody", "Converts incoming JSON request body into a Java object."),
        ("@Service", "Marks business logic class. Spring manages it as a bean."),
        ("@Component", "Generic Spring-managed bean (used for HTTP clients)."),
        ("@Configuration + @Bean", "RestClientConfig creates RestClient as a shared bean."),
        ("@ControllerAdvice", "Global handler for exceptions across all controllers."),
        ("@ExceptionHandler", "Method that handles a specific exception type."),
        ("Dependency Injection", "Constructor receives dependencies. Spring provides them automatically."),
        ("IoC Container", "Spring creates and manages all objects (beans) — you don't use 'new' for services."),
    ]
    story.append(table(["Annotation / Concept", "What It Does in Your Project"], concepts, [5*cm, 11.8*cm]))
    story.append(PageBreak())

    # 12. ISSUES & IMPROVEMENTS
    story.append(Paragraph("12. Issues Found & Recommended Fixes", st["ch"]))
    issues = [
        ("ProductController update/replace bug", "Lines 68-70 and 81-83 use product.getTitle() instead of productdto.getTitle(). Updates don't work."),
        ("Duplicate service call", "getSingleProduct() called twice in getSingleProduct endpoint (lines 38 and 45)."),
        ("Wrong category products URL", "FakeStoreCategoryClient uses /categories/{id}/products. Correct: /products/category/{name}."),
        ("PATCH not supported", "FakeStore only supports PUT. Change restClient.patch() to restClient.put()."),
        ("404 becomes 500", "RestClient throws on FakeStore 404. Add error handling in client layer."),
        ("POST returns 200", "Should return 201 Created for new resources."),
        ("Category CRUD stubs", "POST/PUT/DELETE return plain strings, not real operations."),
        ("Main2.java in src/main", "Scratch file — delete it."),
        ("Hardcoded FakeStore URL", "Move to application.properties."),
        ("No input validation", "Add @Valid and spring-boot-starter-validation."),
        ("No tests", "Only contextLoads test exists. Add controller and client tests."),
    ]
    for title, fix in issues:
        story.append(mistake(f"<b>{title}</b><br/>{fix}"))
    story.append(PageBreak())

    # 13. HOW TO RUN
    story.append(Paragraph("13. How to Run & Test", st["ch"]))
    story.append(box(
        "<b>Start the application:</b><br/>"
        "1. Open terminal in project folder<br/>"
        "2. Run: <code>mvn spring-boot:run</code><br/>"
        "3. Server starts on <b>http://localhost:8080</b><br/><br/>"
        "<b>Test with Postman:</b><br/>"
        "• GET http://localhost:8080/products → all products<br/>"
        "• GET http://localhost:8080/products/1 → single product<br/>"
        "• POST http://localhost:8080/products → create (send JSON body)<br/>"
        "• PUT http://localhost:8080/products/1 → replace product<br/>"
        "• DELETE http://localhost:8080/products/1 → delete product<br/>"
        "• GET http://localhost:8080/categories → all categories<br/><br/>"
        "<b>Build JAR:</b> <code>mvn clean package</code> → creates target/productService-0.0.1-SNAPSHOT.jar",
        GREY_BG, TEAL))
    story.append(Spacer(1, 0.5*cm))
    story.append(HRFlowable(width="100%", thickness=2, color=TEAL))
    story.append(Paragraph(
        "This guide covers your complete project at overview level. "
        "Read sections 4-5 for architecture understanding, section 6 for API reference, and section 12 for fixes.",
        ParagraphStyle("end", fontSize=10, textColor=TEAL, alignment=TA_CENTER)))

    doc.build(story, onFirstPage=header_footer, onLaterPages=header_footer)
    print(f"Created: {OUTPUT}")


if __name__ == "__main__":
    build()
