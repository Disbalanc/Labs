import java.util.ArrayList;
import java.util.List;

public class Fancy {
    private static final long MOD = 1_000_000_007L;
    private final List<Long> vals = new ArrayList<>();
    private long mult = 1;
    private long add = 0;

    public Fancy() {
    }

    public void append(int val) {
        // Сохраняем обратно-трансформированное значение
        // Текущая трансформация: f(x) = mult * x + add
        // Нужно найти x такое, что mult * x + add ≡ val (mod MOD)
        // x = (val - add) * mult^(-1) mod MOD
        long v = ((val - add) % MOD + MOD) % MOD;
        v = v * modInverse(mult) % MOD;
        vals.add(v);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mult = mult * m % MOD;
        add = add * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= vals.size()) return -1;
        // Применяем текущую трансформацию к сохранённому значению
        return (int) ((vals.get(idx) * mult % MOD + add) % MOD);
    }

    // Модульная обратная через малую теорему Ферма: a^(-1) ≡ a^(MOD-2) mod MOD
    private long modInverse(long a) {
        return modPow(a % MOD, MOD - 2);
    }

    // Быстрое возведение в степень по модулю
    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}