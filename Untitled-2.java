import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;

public class Main {

    // ТОЧКА ВХОДА
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("===== ПРАКТИЧЕСКАЯ РАБОТА №2 =====");
            System.out.println("0  — Тема 0. Вводная");
            System.out.println("1  — Тема 1. Базовый синтаксис");
            System.out.println("2  — Тема 2. Управляющие конструкции");
            System.out.println("3  — Тема 3. Массивы и строки");
            System.out.println("4  — Тема 4. Методы и модульность");
            System.out.println("5  — Тема 5. Классы и инкапсуляция");
            System.out.println("6  — Тема 6. Наследование и полиморфизм");
            System.out.println("7  — Тема 7. Интерфейсы");
            System.out.println("8  — Тема 8. Коллекции и Generics");
            System.out.println("9  — Тема 9. Исключения");
            System.out.println("b1 — Блок 1. Авторизация (1–10)");
            System.out.println("b2 — Блок 2. Списки и кэш (11–20)");
            System.out.println("b3 — Блок 3. Сеть (21–30)");
            System.out.println("b4 — Блок 4. UI-состояние (31–40)");
            System.out.println("b5 — Блок 5. Железо и GPS (41–50)");
            System.out.println("all — запустить всё подряд");
            System.out.println("exit — выход");
            System.out.print("Ваш выбор: ");

            String cmd = sc.nextLine().trim();
            if (cmd.equalsIgnoreCase("exit")) break;

            try {
                switch (cmd.toLowerCase()) {
                    case "0"   -> runTheme0();
                    case "1"   -> runTheme1();
                    case "2"   -> runTheme2();
                    case "3"   -> runTheme3();
                    case "4"   -> runTheme4();
                    case "5"   -> runTheme5();
                    case "6"   -> runTheme6();
                    case "7"   -> runTheme7();
                    case "8"   -> runTheme8();
                    case "9"   -> runTheme9();
                    case "b1"  -> runBlock1();
                    case "b2"  -> runBlock2();
                    case "b3"  -> runBlock3();
                    case "b4"  -> runBlock4();
                    case "b5"  -> runBlock5();
                    case "all" -> runAll();
                    default    -> System.out.println("Неизвестная команда");
                }
            } catch (Exception e) {
                System.out.println("Ошибка выполнения: " + e.getMessage());
            }
        }
        sc.close();
    }

    static void section(String title) {
        System.out.println();
        System.out.println("───── " + title + " ─────");
    }

    static void runAll() throws Exception {
        runTheme0(); runTheme1(); runTheme2(); runTheme3(); runTheme4();
        runTheme5(); runTheme6(); runTheme7(); runTheme8(); runTheme9();
        runBlock1(); runBlock2(); runBlock3(); runBlock4(); runBlock5();
        System.out.println();
        System.out.println("=== Все задания выполнены ===");
    }

    // ТЕМА 0
    static void runTheme0() {
        System.out.println("Java for Android is ready!");
    }

    // ТЕМА 1

    static void runTheme1() {
        section("Тема 1. Базовый синтаксис");
        System.out.println("1) dp→px: " + dpToPx(16, 1.5f) + ", "
                + dpToPx(16, 2.0f) + ", " + dpToPx(16, 3.0f));

        long ts = 3_725_000L, s = ts / 1000;
        System.out.printf("2) Таймстемп: %dч %dм %dс%n", s / 3600, (s % 3600) / 60, s % 60);

        System.out.printf("3) Батарея: %.2f ч%n", batteryLifeHours(5000, 300, 200));

        System.out.println("4) Координаты: " + isValidCoordinates(54, 38)
                + ", " + isValidCoordinates(100, 38));

        int CAMERA = 1, LOCATION = 2, STORAGE = 4;
        int perms = 0;
        perms |= CAMERA; perms |= LOCATION;
        System.out.println("5) Камера: " + ((perms & CAMERA) != 0)
                + ", Хранилище: " + ((perms & STORAGE) != 0));
        perms &= ~LOCATION;
        System.out.println("   После снятия LOCATION: " + perms);
    }

    static int dpToPx(float dp, float d) { return (int) (dp * d + 0.5f); }
    static double batteryLifeHours(int cap, int s, int n) {
        int draw = s + n;
        return draw <= 0 ? Double.POSITIVE_INFINITY : (double) cap / draw;
    }
    static boolean isValidCoordinates(double lat, double lon) {
        return lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
    }

    // ТЕМА 2

    static void runTheme2() {
        section("Тема 2. Управляющие конструкции");
        System.out.println("1) Ориентация: " + orientation(1080, 1920)
                + ", " + orientation(1920, 1080) + ", " + orientation(100, 100));

        System.out.println("2) HTTP: " + httpCategory(200) + ", "
                + httpCategory(404) + ", " + httpCategory(500));

        System.out.println("3) Backoff:");
        for (int a = 1; a <= 5; a++)
            System.out.println("   Попытка " + a + ": " + ((long) Math.pow(2, a - 1) * 1000) + " мс");

        System.out.println("4) Пропуск пакетов:");
        for (int id : new int[]{101, 102, -1, 104, 105, 0, 106}) {
            if (id == 0) break;
            if (id == -1) continue;
            System.out.println("   Обработка: " + id);
        }

        System.out.println("5) PIN-симуляция:");
        simulatePin("1234", "1234");
        simulatePin("0000", "1234");
    }

    static String orientation(int w, int h) {
        if (w == h) return "SQUARE";
        return w > h ? "LANDSCAPE" : "PORTRAIT";
    }
    static String httpCategory(int c) {
        return switch (c / 100) {
            case 1 -> "Информационный";
            case 2 -> "Успешный";
            case 3 -> "Перенаправление";
            case 4 -> "Ошибка клиента";
            case 5 -> "Ошибка сервера";
            default -> "Неизвестный";
        };
    }
    static void simulatePin(String exp, String act) {
        int a = 0; boolean ok = false;
        do { if (exp.equals(act)) { ok = true; break; } a++; } while (a < 3);
        System.out.println("   PIN " + exp + ": " + (ok ? "ОК" : "Отказ"));
    }


    // ТЕМА 3

    static void runTheme3() {
        section("Тема 3. Массивы и строки");
        System.out.println("1) Нормализация: '" + normalizeQuery("   НАУШНИКИ   Bluetooth   ") + "'");

        String[] fr = {"f1", "f2", "f3", "f4", "f5"};
        reverseArray(fr);
        System.out.println("2) Реверс: " + Arrays.toString(fr));

        System.out.println("3) Маска карты: " + maskCard("1234567812345678"));
        System.out.println("4) Пик акселерометра: " + maxSpike(new int[]{1, 2, 8, 3, 10, 1}));
        System.out.println("5) URL: " + buildQuery(
                new String[]{"id", "source"}, new String[]{"452", "push"}));
    }

    static String normalizeQuery(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", " ");
    }
    static void reverseArray(String[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            String t = a[i]; a[i] = a[a.length - 1 - i]; a[a.length - 1 - i] = t;
        }
    }
    static String maskCard(String n) {
        String d = n.replaceAll("\\D", "");
        return d.length() == 16 ? "**** **** **** " + d.substring(12) : n;
    }
    static int maxSpike(int[] r) {
        int m = 0;
        for (int i = 1; i < r.length; i++) m = Math.max(m, Math.abs(r[i] - r[i - 1]));
        return m;
    }
    static String buildQuery(String[] k, String[] v) {
        StringBuilder sb = new StringBuilder("?");
        for (int i = 0; i < k.length; i++) {
            if (i > 0) sb.append("&");
            sb.append(k[i]).append("=").append(v[i]);
        }
        return sb.toString();
    }

  
    // ТЕМА 4
   
    static void runTheme4() {
        section("Тема 4. Методы и модульность");
        System.out.println("1) Email: " + isValidEmail("a@b.ru", true)
                + ", " + isValidEmail("a@b.org", true));
        System.out.println("2) Валюта: " + formatCurrency(1234.5, "₽"));
        System.out.printf("3) Кэш: %.2f МБ%n", cacheMb(1024 * 1024, 512 * 1024, 2 * 1024 * 1024));
        System.out.println("4) Версии: " + compareVersions("1.12.0", "1.9.4")
                + ", " + compareVersions("2.0.0", "2.0.0"));
    }

    static boolean isValidEmail(String e) { return e != null && e.contains("@"); }
    static boolean isValidEmail(String e, boolean chk) {
        return isValidEmail(e) && (!chk || e.endsWith(".ru") || e.endsWith(".com"));
    }
    static String formatCurrency(double a, String s) { return String.format("%.2f %s", a, s); }
    static double cacheMb(long... sz) {
        long t = 0;
        for (long s : sz) t += s;
        return t / (1024.0 * 1024.0);
    }
    static int compareVersions(String v1, String v2) {
        String[] a = v1.split("\\."), b = v2.split("\\.");
        int len = Math.max(a.length, b.length);
        for (int i = 0; i < len; i++) {
            int x = i < a.length ? Integer.parseInt(a[i]) : 0;
            int y = i < b.length ? Integer.parseInt(b[i]) : 0;
            if (x != y) return x > y ? 1 : -1;
        }
        return 0;
    }

    
    // ТЕМА 5
    
    static void runTheme5() {
        section("Тема 5. Классы и инкапсуляция");
        SettingsModel sm = new SettingsModel();
        sm.setVolume(75);
        System.out.println("1) Volume: " + sm.getVolume());

        CartItem item = new CartItem("p1", "Наушники", 2500, 2);
        System.out.println("2) Cart total: " + item.total());

        BadgeCounter bc = new BadgeCounter();
        bc.increment(); bc.increment(); bc.decrement();
        System.out.println("3) Badge: " + bc.getCount());

        System.out.println("4) Session expired: " + new SessionTracker().isExpired());

        GeoPoint gp = new GeoPoint(54, 38);
        System.out.println("5) Geo: " + gp.getLat() + ", " + gp.getLon());
    }

    static class SettingsModel {
        private boolean darkMode;
        private int volume;
        private String language;
        public void setVolume(int v) {
            if (v < 0 || v > 100) throw new IllegalArgumentException("Громкость 0..100");
            this.volume = v;
        }
        public void setDarkMode(boolean d) { darkMode = d; }
        public void setLanguage(String l) { language = l; }
        public int getVolume() { return volume; }
    }
    record CartItem(String id, String title, double price, int count) {
        public double total() { return price * count; }
    }
    static class BadgeCounter {
        private int count;
        public void increment() { count++; }
        public void decrement() { if (count > 0) count--; }
        public int getCount() { return count; }
    }
    static class SessionTracker {
        private final long login = System.currentTimeMillis();
        private long last = login;
        public void touch() { last = System.currentTimeMillis(); }
        public boolean isExpired() { return System.currentTimeMillis() - last > 15 * 60 * 1000; }
    }
    static class GeoPoint {
        private final double lat, lon;
        public GeoPoint(double lat, double lon) {
            if (lat < -90 || lat > 90 || lon < -180 || lon > 180)
                throw new IllegalArgumentException("Некорректные координаты");
            this.lat = lat; this.lon = lon;
        }
        public double getLat() { return lat; }
        public double getLon() { return lon; }
    }

   
    // ТЕМА 6
    
    static void runTheme6() {
        section("Тема 6. Наследование и полиморфизм");
        BaseScreen[] screens = { new LoginScreen(), new HomeScreen(), new SettingsScreen() };
        for (BaseScreen s : screens) s.onOpen();

        for (AnalyticsEvent e : List.of(new ClickEvent(), new PurchaseEvent(), new ScreenViewEvent()))
            e.log();

        for (UiComponent c : List.of(new ButtonComponent(1, "OK"),
                                     new ImageComponent(2, "https://.../logo.png")))
            c.render();

        for (Subscription s : new Subscription[]{new MonthlySub(), new FamilySub(4), new AnnualSub()})
            System.out.printf("  %.2f%n", s.cost());
    }

    static abstract class BaseScreen { abstract void onOpen(); }
    static class LoginScreen extends BaseScreen { void onOpen() { System.out.println("  Login: open"); } }
    static class HomeScreen extends BaseScreen { void onOpen() { System.out.println("  Home: open"); } }
    static class SettingsScreen extends BaseScreen { void onOpen() { System.out.println("  Settings: open"); } }

    static abstract class AnalyticsEvent { abstract void log(); }
    static class ClickEvent extends AnalyticsEvent { void log() { System.out.println("  click"); } }
    static class PurchaseEvent extends AnalyticsEvent { void log() { System.out.println("  purchase"); } }
    static class ScreenViewEvent extends AnalyticsEvent { void log() { System.out.println("  screen_view"); } }

    static abstract class UiComponent {
        final int id;
        UiComponent(int id) { this.id = id; }
        abstract void render();
    }
    static class ButtonComponent extends UiComponent {
        final String text;
        ButtonComponent(int id, String text) { super(id); this.text = text; }
        void render() { System.out.println("  Button[" + id + "]: " + text); }
    }
    static class ImageComponent extends UiComponent {
        final String url;
        ImageComponent(int id, String url) { super(id); this.url = url; }
        void render() { System.out.println("  Image[" + id + "]: " + url); }
    }

    static abstract class Subscription { abstract double cost(); }
    static class MonthlySub extends Subscription { double cost() { return 299.0; } }
    static class FamilySub extends Subscription {
        final int users;
        FamilySub(int u) { users = u; }
        double cost() { return 299.0 + (users - 1) * 150.0; }
    }
    static class AnnualSub extends Subscription { double cost() { return 299.0 * 12 * 0.8; } }

    
    // ТЕМА 7
   
    static void runTheme7() {
        section("Тема 7. Интерфейсы");
        KeyValueStorage store = new MemoryStorage();
        store.save("token", "abc123");
        System.out.println("1) Token: " + store.get("token"));

        ImageLoadCallback cb = new ImageLoadCallback() {
            public void onSuccess(String b) { System.out.println("2) OK: " + b); }
            public void onError(Throwable e) { System.out.println("2) ERR: " + e.getMessage()); }
        };
        cb.onSuccess("bitmap_ref_01");

        BackgroundTaskListener l = p -> System.out.println("3) Progress: " + p + "%");
        l.onProgress(50);
        l.onComplete();

        PredicateValidator<String> notEmpty = s -> s != null && !s.trim().isEmpty();
        System.out.println("5) validate('Hello'): " + notEmpty.validate("Hello")
                + ", '   ': " + notEmpty.validate("   "));
    }

    interface KeyValueStorage {
        void save(String k, String v);
        String get(String k);
        void clear();
    }
    static class MemoryStorage implements KeyValueStorage {
        private final Map<String, String> map = new HashMap<>();
        public void save(String k, String v) { map.put(k, v); }
        public String get(String k) { return map.get(k); }
        public void clear() { map.clear(); }
    }
    interface ImageLoadCallback {
        void onSuccess(String bitmapRef);
        void onError(Throwable error);
    }
    interface BackgroundTaskListener {
        void onProgress(int percentage);
        default void onComplete() { System.out.println("  Done"); }
    }
    @FunctionalInterface
    interface PredicateValidator<T> { boolean validate(T data); }

    
    // ТЕМА 8
 
    static void runTheme8() {
        section("Тема 8. Коллекции и Generics");
        List<String> phones = List.of("+7900", "+7901", "+7900", "+7902");
        System.out.println("1) Unique: " + new ArrayList<>(new LinkedHashSet<>(phones)));

        Queue<String> q = new LinkedList<>(List.of("upload_photo", "send_message"));
        while (!q.isEmpty()) System.out.println("2) Sync: " + q.poll());

        ApiResponse<String> r = new ApiResponse<>(200, "OK", null);
        System.out.println("3) Success: " + r.isSuccessful());

        List<Product> products = new ArrayList<>(List.of(
                new Product("A", 100, 4.5),
                new Product("B", 100, 4.9),
                new Product("C", 50, 5.0)));
        products.sort(Comparator.comparingDouble(Product::price)
                .thenComparing(Comparator.comparingDouble(Product::rating).reversed()));
        System.out.println("4) Sorted: " + products);

        ScreenLru<String, String> lru = new ScreenLru<>(5);
        for (int i = 1; i <= 6; i++) lru.put("S" + i, "state" + i);
        System.out.println("5) LRU size: " + lru.size() + ", keys: " + lru.keySet());
    }

    static class ApiResponse<T> {
        final int statusCode;
        final T data;
        final String errorMessage;
        ApiResponse(int c, T d, String e) { statusCode = c; data = d; errorMessage = e; }
        boolean isSuccessful() { return statusCode >= 200 && statusCode < 300; }
    }
    record Product(String name, double price, double rating) {}
    static class ScreenLru<K, V> extends LinkedHashMap<K, V> {
        private final int max;
        ScreenLru(int max) { super(max, 0.75f, true); this.max = max; }
        @Override protected boolean removeEldestEntry(Map.Entry<K, V> e) { return size() > max; }
    }

  
    // ТЕМА 9
   
    static void runTheme9() {
        section("Тема 9. Исключения");
        try { request(false); }
        catch (NoInternetException e) { System.out.println("1) " + e.getMessage()); }

        try { parseAge("150"); }
        catch (InvalidUserDataException e) { System.out.println("3) " + e.getMessage()); }

        try {
            int[] arr = new int[3]; String s = null; arr[10] = s.length();
        } catch (NullPointerException e) { System.out.println("4) NPE"); }
          catch (IndexOutOfBoundsException e) { System.out.println("4) IOOBE"); }
          catch (Exception e) { System.out.println("4) Other"); }

        Map<String, String> bundle = Map.of("name", "Alex");
        System.out.println("5) name: " + getStringSafe(bundle, "name", "Unknown")
                + ", city: " + getStringSafe(bundle, "city", "Unknown"));
    }

    static class NoInternetException extends Exception { NoInternetException(String m) { super(m); } }
    static void request(boolean c) throws NoInternetException {
        if (!c) throw new NoInternetException("Нет подключения");
        System.out.println("  Запрос выполнен");
    }
    static class InvalidUserDataException extends RuntimeException { InvalidUserDataException(String m) { super(m); } }
    static int parseAge(String s) {
        int a = Integer.parseInt(s);
        if (a < 0 || a > 130) throw new InvalidUserDataException("Возраст: " + a);
        return a;
    }
    static String getStringSafe(Map<String, String> b, String k, String d) {
        try { String v = b.get(k); return v != null ? v : d; }
        catch (Exception e) { return d; }
    }

 
    // БЛОК 1: АВТОРИЗАЦИЯ (1–10)
  
    static void runBlock1() {
        section("Блок 1. Авторизация и валидация");
        System.out.println("1) Password: " + isValidPassword("Passw0rd!")
                + ", " + isValidPassword("password"));
        System.out.println("2) Phone: " + normalizePhone("8 (999) 000-11-22"));
        System.out.println("3) OTP: " + generateOtp());

        long now = System.currentTimeMillis() / 1000;
        System.out.println("4) JWT: " + isJwtActive(now + 3600)
                + ", expired: " + isJwtActive(now - 10));

        System.out.println("5) Masked: " + maskEmail("alexander.ivanov@mail.ru"));

        LoginThrottler t = new LoginThrottler();
        for (int i = 0; i < 5; i++) t.onFailure();
        System.out.println("6) Can attempt: " + t.canAttempt());

        System.out.println("7) Cipher: " + encryptPermutation("Привет")
                + " → " + decryptPermutation(encryptPermutation("Привет")));

        System.out.println("8) Biometric: " + canUseBiometric(true, true, true, false)
                + ", denied: " + canUseBiometric(true, false, true, false));

        System.out.println("9) Session timed out: " + new SessionTimeout().isTimedOut());
        System.out.println("10) Promo: " + isValidPromo("SALE-2026")
                + ", " + isValidPromo("sale-2026"));
    }

    static boolean isValidPassword(String p) {
        if (p == null || p.length() < 8) return false;
        boolean u = false, d = false, s = false;
        String sp = "!@#$%^&*";
        for (char c : p.toCharArray()) {
            if (Character.isUpperCase(c)) u = true;
            else if (Character.isDigit(c)) d = true;
            else if (sp.indexOf(c) >= 0) s = true;
        }
        return u && d && s;
    }
    static String normalizePhone(String r) {
        String d = r.replaceAll("\\D", "");
        if (d.startsWith("8") && d.length() == 11) d = "7" + d.substring(1);
        if (d.length() == 10) d = "7" + d;
        return "+" + d;
    }
    static String generateOtp() {
        return String.format("%06d", new SecureRandom().nextInt(1_000_000));
    }
    static boolean isJwtActive(long exp) { return exp > System.currentTimeMillis() / 1000; }
    static String maskEmail(String e) {
        int at = e.indexOf('@');
        if (at <= 1) return e;
        String l = e.substring(0, at);
        StringBuilder sb = new StringBuilder().append(l.charAt(0));
        for (int i = 1; i < l.length() - 1; i++) sb.append('*');
        if (l.length() > 1) sb.append(l.charAt(l.length() - 1));
        return sb + e.substring(at);
    }
    static class LoginThrottler {
        private int failed = 0;
        private long blocked = 0;
        public boolean canAttempt() { return System.currentTimeMillis() >= blocked; }
        public void onFailure() {
            failed++;
            if (failed >= 5) { blocked = System.currentTimeMillis() + 60_000; failed = 0; }
        }
    }
    static String encryptPermutation(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length - 1; i += 2) {
            char t = c[i]; c[i] = c[i + 1]; c[i + 1] = t;
        }
        return new String(c);
    }
    static String decryptPermutation(String s) { return encryptPermutation(s); }
    static boolean canUseBiometric(boolean hw, boolean en, boolean p, boolean l) {
        return hw && en && p && !l;
    }
    static class SessionTimeout {
        private final long last = System.currentTimeMillis();
        public boolean isTimedOut() { return System.currentTimeMillis() - last > 3 * 60 * 1000; }
    }
    static boolean isValidPromo(String c) {
        return c != null && c.matches("[A-Z]{4}-\\d{4}");
    }

  
    // БЛОК 2: СПИСКИ И КЭШ (11–20)

    static void runBlock2() {
        section("Блок 2. Списки и кэш");
        List<NewsItem> oldN = List.of(new NewsItem("1", "A"), new NewsItem("2", "B"));
        List<NewsItem> newN = List.of(new NewsItem("1", "A2"), new NewsItem("3", "C"));
        System.out.println("11) Diff: " + diffNews(oldN, newN));

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 45; i++) data.add(i);
        System.out.println("12) Page 2: " + paginate(data, 2, 20));

        System.out.println("13) Group: " + groupContacts(
                List.of("Анна", "Борис", "Алексей", "Виктор")));

        System.out.println("14) Filter: " + filterProducts(List.of(
                new ProductItem("Наушники", "ART-1"),
                new ProductItem("Часы", "ART-2")), "науш"));

        String[] banners = {"b1", "b2", "b3"};
        System.out.println("15) Next: " + banners[(2 + 1) % banners.length]);

        System.out.println("16) Cart total: " + cartTotal(
                List.of(new CartLine("electronics", 1000, 2),
                        new CartLine("books", 500, 1)),
                Map.of("electronics", 0.1)));

        UndoBuffer ub = new UndoBuffer();
        ub.remove("note_42");
        System.out.println("17) Undo: " + ub.undo());

        System.out.println("18) Chats: " + sortChats(List.of(
                new Chat("A", 1000), new Chat("B", 5000), new Chat("C", 3000))));

        System.out.println("19) Duplicates: " + findDuplicates(List.of(
                new FileItem("a.jpg", 100), new FileItem("a.jpg", 100),
                new FileItem("b.jpg", 200))));

        ImageCacheFifo cache = new ImageCacheFifo();
        cache.put("a", 60L * 1024 * 1024);
        cache.put("b", 50L * 1024 * 1024);
        System.out.println("20) Cache size: " + cache.size());
    }

    record NewsItem(String id, String title) {}
    static Map<String, List<String>> diffNews(List<NewsItem> oldL, List<NewsItem> newL) {
        Map<String, String> om = new HashMap<>();
        for (NewsItem n : oldL) om.put(n.id(), n.title());
        List<String> ch = new ArrayList<>(), rm = new ArrayList<>();
        Set<String> nid = new HashSet<>();
        for (NewsItem n : newL) {
            nid.add(n.id());
            String p = om.get(n.id());
            if (p == null || !p.equals(n.title())) ch.add(n.id());
        }
        for (NewsItem n : oldL) if (!nid.contains(n.id())) rm.add(n.id());
        Map<String, List<String>> r = new HashMap<>();
        r.put("changed", ch); r.put("removed", rm);
        return r;
    }
    static <T> List<T> paginate(List<T> all, int p, int sz) {
        int f = p * sz;
        if (f >= all.size()) return Collections.emptyList();
        return all.subList(f, Math.min(f + sz, all.size()));
    }
    static Map<Character, List<String>> groupContacts(List<String> names) {
        Map<Character, List<String>> m = new TreeMap<>();
        for (String n : names) {
            char k = Character.toUpperCase(n.charAt(0));
            m.computeIfAbsent(k, x -> new ArrayList<>()).add(n);
        }
        return m;
    }
    record ProductItem(String name, String article) {}
    static List<ProductItem> filterProducts(List<ProductItem> l, String q) {
        String qq = q.toLowerCase();
        List<ProductItem> r = new ArrayList<>();
        for (ProductItem p : l)
            if (p.name().toLowerCase().contains(qq) || p.article().toLowerCase().contains(qq))
                r.add(p);
        return r;
    }
    record CartLine(String category, double price, int count) {}
    static double cartTotal(List<CartLine> lines, Map<String, Double> disc) {
        double s = 0;
        for (CartLine l : lines) {
            double d = disc.getOrDefault(l.category(), 0.0);
            s += l.price() * l.count() * (1 - d);
        }
        return s;
    }
    static class UndoBuffer {
        private String buf; private long at;
        public void remove(String i) { buf = i; at = System.currentTimeMillis(); }
        public String undo() {
            if (buf != null && System.currentTimeMillis() - at < 5000) {
                String s = buf; buf = null; return s;
            }
            return null;
        }
    }
    record Chat(String user, long ts) {}
    static List<Chat> sortChats(List<Chat> c) {
        List<Chat> r = new ArrayList<>(c);
        r.sort(Comparator.comparingLong(Chat::ts).reversed());
        return r;
    }
    record FileItem(String name, long size) {}
    static List<FileItem> findDuplicates(List<FileItem> files) {
        Map<String, Integer> seen = new HashMap<>();
        List<FileItem> dup = new ArrayList<>();
        for (FileItem f : files) {
            String k = f.name() + "|" + f.size();
            if (seen.merge(k, 1, Integer::sum) == 2) dup.add(f);
        }
        return dup;
    }
    static class ImageCacheFifo {
        record Entry(String url, long size) {}
        private final Deque<Entry> fifo = new ArrayDeque<>();
        private long cur = 0;
        private static final long LIMIT = 100L * 1024 * 1024;
        public void put(String u, long s) {
            while (cur + s > LIMIT && !fifo.isEmpty()) cur -= fifo.pollFirst().size();
            fifo.addLast(new Entry(u, s)); cur += s;
        }
        public int size() { return fifo.size(); }
    }

  
    // БЛОК 3: СЕТЬ (21–30)
  
    static void runBlock3() throws Exception {
        section("Блок 3. Сеть и офлайн");
        System.out.println("21) DeepLink: " + parseDeepLink("app://shop/product?id=452&source=push"));
        System.out.println("22) Retry: " + retryRequest(2));

        OfflineQueue oq = new OfflineQueue();
        oq.push("like:post_1"); o
                oq.push("comment:post_2");
        oq.setOnline(true);
        oq.flush();

        System.out.println("24) Conflict: " + resolveConflict(new Note(1, "L"), new Note(2, "S")));
        System.out.printf("25) Speed: %.2f KB/s, %.2f Mbit/s%n",
                kbPerSec(2_000_000, 4000), mbitPerSec(2_000_000, 4000));
        System.out.println("26) Next page: "
                + nextPage("<https://api.com/items?page=3>; rel=\"next\""));
        System.out.println("27) ETag: " + checkEtag("\"abc\"", "\"abc\"")
                + ", changed: " + checkEtag("\"abc\"", "\"xyz\""));
        System.out.println("28) Bytes: " + formatBytes(1024) + ", "
                + formatBytes(1536) + ", " + formatBytes(1048576));

        QuoteFeed feed = new QuoteFeed();
        feed.subscribe((p, v) -> System.out.println("29) Quote: " + p + " = " + v));
        feed.emit("USD/RUB", 92.5);

        Map<String, Object> profile = new HashMap<>();
        profile.put("name", "Alex");
        System.out.println("30) Missing: " + missingFields(profile, "name", "email", "phone"));
    }

    static Map<String, String> parseDeepLink(String url) {
        Map<String, String> r = new LinkedHashMap<>();
        int q = url.indexOf('?');
        if (q == -1) return r;
        for (String p : url.substring(q + 1).split("&")) {
            String[] kv = p.split("=", 2);
            r.put(kv[0], kv.length > 1 ? kv[1] : "");
        }
        return r;
    }

    static String retryRequest(int failCount) throws Exception {
        int attempts = 0;
        while (true) {
            try {
                attempts++;
                if (attempts <= failCount) throw new SocketTimeoutException("timeout");
                return "OK after " + attempts;
            } catch (SocketTimeoutException e) {
                if (attempts >= 3) throw e;
            }
        }
    }

    static class OfflineQueue {
        private final Deque<String> queue = new ArrayDeque<>();
        private boolean online = false;
        public void setOnline(boolean o) { online = o; }
        public void push(String a) {
            if (online) System.out.println("  Отправка сразу: " + a);
            else queue.addLast(a);
        }
        public void flush() {
            if (!online) return;
            while (!queue.isEmpty()) System.out.println("  Из очереди: " + queue.pollFirst());
        }
    }

    record Note(long version, String text) {}
    static Note resolveConflict(Note local, Note server) {
        if (server.version() > local.version()) return server;
        if (local.version() > server.version()) {
            System.out.println("  Локальная новее — отправляем на сервер");
            return local;
        }
        return local;
    }

    static double kbPerSec(long b, long ms) { return (b / 1024.0) / (ms / 1000.0); }
    static double mbitPerSec(long b, long ms) {
        return (b * 8.0 / 1_000_000.0) / (ms / 1000.0);
    }

    static Integer nextPage(String h) {
        Matcher m = Pattern.compile("page=(\\d+)").matcher(h);
        return m.find() ? Integer.parseInt(m.group(1)) : null;
    }

    static String checkEtag(String saved, String server) {
        return (saved != null && saved.equals(server)) ? "304 Not Modified" : "200 OK body";
    }

    static String formatBytes(long b) {
        if (b < 1024) return b + " B";
        double kb = b / 1024.0;
        if (kb < 1024) return String.format("%.1f KB", kb);
        double mb = kb / 1024.0;
        if (mb < 1024) return String.format("%.1f MB", mb);
        return String.format("%.1f GB", mb / 1024.0);
    }

    interface QuoteListener { void onQuote(String pair, double price); }
    static class QuoteFeed {
        private final List<QuoteListener> ls = new ArrayList<>();
        public void subscribe(QuoteListener l) { ls.add(l); }
        public void emit(String p, double v) { for (QuoteListener l : ls) l.onQuote(p, v); }
    }

    static List<String> missingFields(Map<String, Object> o, String... req) {
        List<String> r = new ArrayList<>();
        for (String f : req) if (o.get(f) == null) r.add(f);
        return r;
    }

    // БЛОК 4: UI-СОСТОЯНИЕ (31–40)

    static void runBlock4() {
        section("Блок 4. UI-состояние");

        System.out.println("31) UI: " + UiState.success("data"));

        ClickDebouncer deb = new ClickDebouncer();
        boolean a = deb.tryClick(), b = deb.tryClick();
        System.out.println("32) Debouncer: " + a + " / " + b);

        BackStack bs = new BackStack();
        bs.push("Home"); bs.push("Settings"); bs.push("Profile");
        bs.popToRoot();
        System.out.println("33) BackStack current: " + bs.current());

        System.out.println("34) Theme: " + ThemePalette.background(true)
                + ", " + ThemePalette.text(false));
        System.out.println("35) Profile fill: "
                + profileProgress(true, true, false, false, true) + "%");
        System.out.println("36) Contrast on white: " + contrastColor(255, 255, 255)
                + ", on black: " + contrastColor(0, 0, 0));
        System.out.println("37) Count: " + formatCount(950) + ", "
                + formatCount(1200) + ", " + formatCount(1_500_000));

        DialogManager dm = new DialogManager();
        dm.enqueue("A"); dm.enqueue("B"); dm.dismiss();

        System.out.println("39) Pay enabled: " + payEnabled(true, true, false)
                + ", all ok: " + payEnabled(true, true, true));
        System.out.println("40) Error RU: " + translateError(new UnknownHostException("api.com")));
    }

    enum UiStateKind { LOADING, SUCCESS, EMPTY, ERROR }
    record UiState(UiStateKind kind, String data, String error) {
        static UiState loading() { return new UiState(UiStateKind.LOADING, null, null); }
        static UiState success(String d) { return new UiState(UiStateKind.SUCCESS, d, null); }
        static UiState empty() { return new UiState(UiStateKind.EMPTY, null, null); }
        static UiState error(String e) { return new UiState(UiStateKind.ERROR, null, e); }
    }

    static class ClickDebouncer {
        private long last = 0;
        public boolean tryClick() {
            long now = System.currentTimeMillis();
            if (now - last < 500) return false;
            last = now; return true;
        }
    }

    static class BackStack {
        private final Deque<String> st = new ArrayDeque<>();
        public void push(String s) { st.push(s); }
        public String pop() { return st.isEmpty() ? null : st.pop(); }
        public void popToRoot() { while (st.size() > 1) st.pop(); }
        public String current() { return st.peek(); }
    }

    static class ThemePalette {
        static String background(boolean d) { return d ? "#121212" : "#FFFFFF"; }
        static String text(boolean d) { return d ? "#FFFFFF" : "#000000"; }
        static String primary(boolean d) { return d ? "#BB86FC" : "#6200EE"; }
    }

    static int profileProgress(boolean av, boolean bio, boolean ph, boolean em, boolean ct) {
        int f = 0;
        if (av) f++; if (bio) f++; if (ph) f++; if (em) f++; if (ct) f++;
        return f * 100 / 5;
    }

    static String contrastColor(int r, int g, int b) {
        double yiq = (r * 299 + g * 587 + b * 114) / 1000.0;
        return yiq >= 128 ? "#000000" : "#FFFFFF";
    }

    static String formatCount(long n) {
        if (n < 1000) return String.valueOf(n);
        if (n < 1_000_000) return String.format("%.1fK", n / 1000.0);
        return String.format("%.1fM", n / 1_000_000.0);
    }

    static class DialogManager {
        private final Deque<String> q = new ArrayDeque<>();
        private boolean shown = false;
        public void enqueue(String d) {
            if (shown) q.addLast(d);
            else { shown = true; System.out.println("  Показ: " + d); }
        }
        public void dismiss() {
            shown = false;
            if (!q.isEmpty()) enqueue(q.pollFirst());
        }
    }

    static boolean payEnabled(boolean cart, boolean pay, boolean addr) {
        return cart && pay && addr;
    }

    static String translateError(Exception e) {
        if (e instanceof SocketTimeoutException)
            return "Превышено время ожидания. Проверьте соединение.";
        if (e instanceof UnknownHostException)
            return "Сервер недоступен. Проверьте интернет.";
        return "Что-то пошло не так: " + e.getMessage();
    }

    // БЛОК 5: ЖЕЛЕЗО И GPS (41–50)

    static void runBlock5() {
        section("Блок 5. Железо, GPS, фон");

        System.out.printf("41) Distance: %.0f м%n",
                distanceMeters(54.0105, 38.2917, 54.0200, 38.3000));
        System.out.println("42) Inside geofence: "
                + insideGeofence(54.0105, 38.2917, 54.0105, 38.2917, 100));
        System.out.println("43) GPS 80%: " + gpsInterval(80) + " сек");
        System.out.println("43) GPS 30%: " + gpsInterval(30) + " сек");
        System.out.println("43) GPS 5%:  " + gpsInterval(5) + " сек");
        System.out.println("44) Fall: " + isFall(0, 0, 0.5)
                + ", normal: " + isFall(0, 0, 9.8));
        System.out.println("45) Steps: " + countSteps(
                new double[]{9.8, 9.8, 12.0, 9.8, 13.0, 9.8, 11.5, 9.8}, 10.5));
        System.out.println("46) Brightness 1 lux: " + brightnessPercent(1) + "%");
        System.out.println("46) Brightness 100 lux: " + brightnessPercent(100) + "%");
        System.out.println("46) Brightness 10000 lux: " + brightnessPercent(10000) + "%");
        System.out.println("47) Bg sync: " + canBgSync(true, true)
                + ", no charging: " + canBgSync(true, false));

        TrafficMonitor tm = new TrafficMonitor();
        tm.addWifi(2L * 1024 * 1024 * 1024);
        tm.addMobile(3L * 1024 * 1024 * 1024);
        tm.addMobile(3L * 1024 * 1024 * 1024);
        System.out.println("48) Mobile: " + tm.getMobile() + " байт");
        System.out.println("48) Wi-Fi:  " + tm.getWifi() + " байт");

        AudioPlayer player = new AudioPlayer();
        player.play();
        player.init();
        player.prepare();
        player.play();
        player.pause();
        player.play();
        player.stop();
        System.out.println("49) Player state: " + player.getState());

        try {
            int[] arr = new int[3];
            arr[10] = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            DeviceInfo dev = new DeviceInfo("Android 14", "Pixel 7", 3L * 1024 * 1024 * 1024);
            System.out.println("50) Crash report:");
            System.out.println(buildCrashReport(e, dev));
        }
    }

    static double distanceMeters(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6_371_000;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    static boolean insideGeofence(double lat, double lon,
                                  double cLat, double cLon, double radius) {
        return distanceMeters(lat, lon, cLat, cLon) <= radius;
    }

    static int gpsInterval(int p) {
        if (p > 50) return 5;
        if (p >= 15) return 30;
        return 300;
    }

    static boolean isFall(double x, double y, double z) {
        double m = Math.sqrt(x * x + y * y + z * z);
        return m < 1.0 || m > 25.0;
    }

    static int countSteps(double[] acc, double threshold) {
        int s = 0;
        for (int i = 1; i < acc.length - 1; i++)
            if (acc[i] > acc[i - 1] && acc[i] > acc[i + 1] && acc[i] > threshold) s++;
        return s;
    }

    static int brightnessPercent(double lux) {
        if (lux <= 0) return 0;
        double log = Math.log10(lux + 1);
        double max = Math.log10(10_000 + 1);
        return (int) Math.min(100, log / max * 100);
    }

    static boolean canBgSync(boolean wifi, boolean charging) {
        return wifi && charging;
    }

    static class TrafficMonitor {
        private long mobile = 0, wifi = 0;
        private static final long LIMIT = 5L * 1024 * 1024 * 1024;
        public void addMobile(long b) {
            mobile += b;
            if (mobile >= LIMIT) System.out.println("  ⚠ Лимит мобильной сети превышен");
        }
        public void addWifi(long b) { wifi += b; }
        public long getMobile() { return mobile; }
        public long getWifi() { return wifi; }
    }

    static class AudioPlayer {
        enum State { IDLE, INITIALIZED, PREPARED, PLAYING, PAUSED, STOPPED }
        private State state = State.IDLE;
        public State getState() { return state; }
        public boolean init() {
            if (state != State.IDLE) return rej("init");
            state = State.INITIALIZED; return true;
        }
        public boolean prepare() {
            if (state != State.INITIALIZED && state != State.STOPPED) return rej("prepare");
            state = State.PREPARED; return true;
        }
        public boolean play() {
            if (state != State.PREPARED && state != State.PAUSED) return rej("play");
            state = State.PLAYING; return true;
        }
        public boolean pause() {
            if (state != State.PLAYING) return rej("pause");
            state = State.PAUSED; return true;
        }
        public boolean stop() {
            if (state != State.PLAYING && state != State.PAUSED) return rej("stop");
            state = State.STOPPED; return true;
        }
        private boolean rej(String a) {
            System.out.println("  ✗ Недопустимый переход: " + a + " в " + state);
            return false;
        }
    }

    record DeviceInfo(String androidVersion, String model, long freeSpace) {}

    static String buildCrashReport(Throwable error, DeviceInfo dev) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ===== CRASH REPORT =====\n");
        sb.append("  Время: ")
          .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
          .append("\n");
        sb.append("  Android: ").append(dev.androidVersion()).append("\n");
        sb.append("  Модель:  ").append(dev.model()).append("\n");
        sb.append("  Свободно: ")
          .append(String.format("%.1f МБ", dev.freeSpace() / (1024.0 * 1024.0)))
          .append("\n");
        sb.append("  Исключение: ")
          .append(error.getClass().getSimpleName())
          .append(": ").append(error.getMessage()).append("\n");
        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw));
        sb.append(sw);
        sb.append("  ========================");
        return sb.toString();
    }

} 