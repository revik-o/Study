#include <gtk/gtk.h>
#include <webkit/webkit.h>

#define DEFAULT_WINDOW_HEIGHT 640
#define DEFAULT_WINDOW_WIDTH 480
#define SPLASH_LOGO_SIZE (DEFAULT_WINDOW_HEIGHT / 5)

static void on_script_message_received(WebKitUserContentManager *manager, JSCValue *js_value, gpointer user_data) {
    (void)manager;
    (void)user_data;

    char *message = jsc_value_to_string(js_value);

    g_print("\n!!! JS INVOKED: %s !!!\n\n", message);

    g_free(message);
}

static void on_load_changed(WebKitWebView *webview, WebKitLoadEvent load_event, gpointer user_data)
{
    GtkStack *stack = GTK_STACK(user_data);

    if (load_event == WEBKIT_LOAD_FINISHED)
    {
        gtk_stack_set_visible_child_name(stack, "browser");
        g_print("Load status: done\n");
    }

    (void)webview;
}

static void activate(GtkApplication *app, gpointer user_data)
{
    (void)user_data;

    GtkWidget *stack;
    GtkWidget *window;
    GtkWidget *splash_image;

    g_object_set(gtk_settings_get_default(), "gtk-application-prefer-dark-theme", TRUE, NULL);

    window = gtk_application_window_new(app);
    gtk_window_set_title(GTK_WINDOW(window), "My Global IP");
    gtk_window_set_default_size(GTK_WINDOW(window), DEFAULT_WINDOW_HEIGHT, DEFAULT_WINDOW_WIDTH);

    stack = gtk_stack_new();
    gtk_stack_set_transition_type(GTK_STACK(stack), GTK_STACK_TRANSITION_TYPE_CROSSFADE);

    GError *error = NULL;
    GdkPixbuf *original_pixbuf = gdk_pixbuf_new_from_file("assets/logo.png", &error);

    if (!original_pixbuf)
    {
        g_error("NO_FILE %s", error->message);
    }

    GdkPixbuf *scaled_pixbuf = gdk_pixbuf_scale_simple(original_pixbuf, SPLASH_LOGO_SIZE, SPLASH_LOGO_SIZE, GDK_INTERP_BILINEAR);

    GdkTexture *texture = gdk_texture_new_for_pixbuf(scaled_pixbuf);
    splash_image = gtk_image_new_from_paintable(GDK_PAINTABLE(texture));

    gtk_widget_set_size_request(splash_image, SPLASH_LOGO_SIZE, SPLASH_LOGO_SIZE);
    gtk_widget_set_halign(splash_image, GTK_ALIGN_CENTER);
    gtk_widget_set_valign(splash_image, GTK_ALIGN_CENTER);
    g_object_unref(original_pixbuf);
    g_object_unref(scaled_pixbuf);
    g_object_unref(texture);

    gtk_stack_add_named(GTK_STACK(stack), splash_image, "splash");
    gtk_stack_set_visible_child_name(GTK_STACK(stack), "splash");
    gtk_window_set_child(GTK_WINDOW(window), stack);
    gtk_window_present(GTK_WINDOW(window));

    WebKitUserContentManager *manager = webkit_user_content_manager_new();

    webkit_user_content_manager_register_script_message_handler(manager, "mySignal", NULL);
    g_signal_connect(manager, "script-message-received::mySignal", G_CALLBACK(on_script_message_received), NULL);

    GtkWidget *webview = g_object_new(WEBKIT_TYPE_WEB_VIEW, "user-content-manager", manager, NULL);

    char *cwd = g_get_current_dir();
    char *html_path = g_strdup_printf("file://%s/assets/index.html", cwd);

    webkit_web_view_load_uri(WEBKIT_WEB_VIEW(webview), html_path);

    g_free(cwd);
    g_free(html_path);

    gtk_stack_add_named(GTK_STACK(stack), webview, "browser");

    g_signal_connect(webview, "load-changed", G_CALLBACK(on_load_changed), stack);
}

int main(int argc, char **argv)
{
    GtkApplication *app;
    int status;
    app = gtk_application_new("com.example.seniorapp", G_APPLICATION_DEFAULT_FLAGS);
    g_signal_connect(app, "activate", G_CALLBACK(activate), NULL);
    status = g_application_run(G_APPLICATION(app), argc, argv);
    g_object_unref(app);
    return status;
}