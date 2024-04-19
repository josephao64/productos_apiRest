import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private List<Producto> productos = new ArrayList<>();
    private Long idProducto = 1L;

    @GetMapping
    public List<Producto> listarProductos() {
        return productos;
    }

    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productos.stream().filter(p -> p.getId().equals(id)).findFirst();
        return producto.orElse(null);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto nuevoProducto) {
        nuevoProducto.setId(idProducto++);
        productos.add(nuevoProducto);
        return nuevoProducto;
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Optional<Producto> producto = productos.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (producto.isPresent()) {
            Producto p = producto.get();
            p.setNombre(productoActualizado.getNombre());
            p.setMarca(productoActualizado.getMarca());
            p.setDescripcion(productoActualizado.getDescripcion());
            p.setPrecio(productoActualizado.getPrecio());
            return p;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void borrarProducto(@PathVariable Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }
}
