package mx.edu.tecdesotware.Market_Backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesotware.Market_Backend.domain.Purchase;
import mx.edu.tecdesotware.Market_Backend.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "Manage purchases in the store")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("")
    @Operation(summary = "Get all purchases",
            description = "Return a list of all purchases")
    @ApiResponse(
            responseCode = "200",
                description = "Purchases succesfully retrieved"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Purchases not found"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @Operation(summary = "Get a puchases by its client",
            description = "Return a list of purchases by its client ID if it exists")
    @ApiResponse(
            responseCode = "200",
            description = "Purchases found"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Purchases not found"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save a new purchases",
            description = "Register a new purchase and return the created purchase",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Example purchases",
                                    value = """
                                {
                                  "clientId": "4546221",
                                  "date": "2026-07-16T14:33:37.335Z",
                                  "paymentMethod": "E",
                                  "comment": "Compra",
                                  "status": "T",
                                  "items": [
                                    {
                                      "productId": 4,
                                      "quantity": 1,
                                      "price": 0.1,
                                      "status": true
                                    }
                                  ]
                                }
                                """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Purchase conflict (duplicate code)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}