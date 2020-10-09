package tour.idioms;

public final class Gson {
    public <T> T fromJson(JsonElement json, Class<T> classOfT) throws Exception {
        return (T)json;
    }
}