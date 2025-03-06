using Microsoft.AspNetCore.Mvc;
using practice.Models;

namespace practice.Controllers;

public class ItemsController : Controller
{

    public IActionResult Overview()
    {
        var item = new Item() { Name = "test" };
        return View(item);
    }
}