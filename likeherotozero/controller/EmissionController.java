@Controller
public class EmissionController {

    @Autowired
    private EmissionRepository repo;

    @GetMapping("/")
    public String home() {
        return "index";
    }
}