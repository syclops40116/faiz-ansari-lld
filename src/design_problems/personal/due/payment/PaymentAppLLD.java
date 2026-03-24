// ============================================================
// PAYMENT APP - LOW LEVEL DESIGN (Interview Ready)
// ============================================================
// Design Patterns Used:
//   1. Singleton       - PaymentService, NotificationService
//   2. Factory         - PaymentProcessorFactory
//   3. Strategy        - Payment methods (UPI, Card, Wallet, NetBanking)
//   4. Observer        - Transaction listeners / Notifications
//   5. Builder         - Transaction, User objects
//   6. State           - Transaction lifecycle states
//   7. Command         - Payment & Refund commands
//   8. Decorator       - Fee calculation (GST, platform fee)
//   9. Repository      - UserRepository, TransactionRepository
//  10. Facade          - PaymentFacade (simplified client API)
// ============================================================

package design_problems.personal.due.payment;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

// ─────────────────────────────────────────────
// ENUMS
// ─────────────────────────────────────────────

enum TransactionStatus {
    INITIATED, PROCESSING, SUCCESS, FAILED, REFUNDED, CANCELLED
}

enum PaymentMethod {
    UPI, CREDIT_CARD, DEBIT_CARD, WALLET, NET_BANKING
}

enum NotificationType {
    SMS, EMAIL, PUSH
}

// ─────────────────────────────────────────────
// 1. BUILDER PATTERN - User & Transaction
// ─────────────────────────────────────────────

class User {
    private final String userId;
    private final String name;
    private final String email;
    private final String phone;
    private final double walletBalance;

    private User(Builder b) {
        this.userId        = b.userId;
        this.name          = b.name;
        this.email         = b.email;
        this.phone         = b.phone;
        this.walletBalance = b.walletBalance;
    }

    public String getUserId()        { return userId; }
    public String getName()          { return name; }
    public String getEmail()         { return email; }
    public String getPhone()         { return phone; }
    public double getWalletBalance() { return walletBalance; }

    @Override
    public String toString() {
        return String.format("User[id=%s, name=%s, wallet=₹%.2f]", userId, name, walletBalance);
    }

    public static class Builder {
        private String userId;
        private String name;
        private String email;
        private String phone;
        private double walletBalance = 0.0;

        public Builder userId(String v)        { this.userId = v;        return this; }
        public Builder name(String v)          { this.name = v;          return this; }
        public Builder email(String v)         { this.email = v;         return this; }
        public Builder phone(String v)         { this.phone = v;         return this; }
        public Builder walletBalance(double v) { this.walletBalance = v; return this; }
        public User build()                    { return new User(this); }
    }
}

class Transaction {
    private final String transactionId;
    private final String senderId;
    private final String receiverId;
    private final double amount;
    private final double fees;
    private final PaymentMethod method;
    private TransactionStatus status;
    private final LocalDateTime createdAt;
    private String failureReason;

    private Transaction(Builder b) {
        this.transactionId = b.transactionId;
        this.senderId      = b.senderId;
        this.receiverId    = b.receiverId;
        this.amount        = b.amount;
        this.fees          = b.fees;
        this.method        = b.method;
        this.status        = TransactionStatus.INITIATED;
        this.createdAt     = LocalDateTime.now();
    }

    public String getTransactionId()    { return transactionId; }
    public String getSenderId()         { return senderId; }
    public String getReceiverId()       { return receiverId; }
    public double getAmount()           { return amount; }
    public double getFees()             { return fees; }
    public double getTotalAmount()      { return amount + fees; }
    public PaymentMethod getMethod()    { return method; }
    public TransactionStatus getStatus(){ return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getFailureReason()    { return failureReason; }

    public void setStatus(TransactionStatus s)   { this.status = s; }
    public void setFailureReason(String reason)  { this.failureReason = reason; }

    @Override
    public String toString() {
        return String.format(
            "Txn[id=%s, ₹%.2f + fees ₹%.2f, %s via %s, status=%s]",
            transactionId, amount, fees, senderId + "→" + receiverId, method, status
        );
    }

    public static class Builder {
        private String transactionId = "TXN" + System.currentTimeMillis();
        private String senderId;
        private String receiverId;
        private double amount;
        private double fees = 0.0;
        private PaymentMethod method;

        public Builder senderId(String v)    { this.senderId    = v; return this; }
        public Builder receiverId(String v)  { this.receiverId  = v; return this; }
        public Builder amount(double v)      { this.amount      = v; return this; }
        public Builder fees(double v)        { this.fees        = v; return this; }
        public Builder method(PaymentMethod m){ this.method     = m; return this; }
        public Transaction build()           { return new Transaction(this); }
    }
}

// ─────────────────────────────────────────────
// 2. STRATEGY PATTERN - Payment Processors
// ─────────────────────────────────────────────

interface PaymentProcessor {
    boolean process(Transaction txn);
    boolean refund(Transaction txn);
    PaymentMethod getMethod();
}

class UPIProcessor implements PaymentProcessor {
    @Override
    public boolean process(Transaction txn) {
        System.out.println("[UPI] Processing ₹" + txn.getAmount() + " for txn " + txn.getTransactionId());
        // Simulate UPI call
        return Math.random() > 0.1; // 90% success
    }
    @Override
    public boolean refund(Transaction txn) {
        System.out.println("[UPI] Initiating refund for txn " + txn.getTransactionId());
        return true;
    }
    @Override public PaymentMethod getMethod() { return PaymentMethod.UPI; }
}

class CreditCardProcessor implements PaymentProcessor {
    @Override
    public boolean process(Transaction txn) {
        System.out.println("[Credit Card] Processing ₹" + txn.getAmount() + " for txn " + txn.getTransactionId());
        return Math.random() > 0.15; // 85% success
    }
    @Override
    public boolean refund(Transaction txn) {
        System.out.println("[Credit Card] Initiating refund for txn " + txn.getTransactionId());
        return true;
    }
    @Override public PaymentMethod getMethod() { return PaymentMethod.CREDIT_CARD; }
}

class WalletProcessor implements PaymentProcessor {
    @Override
    public boolean process(Transaction txn) {
        System.out.println("[Wallet] Processing ₹" + txn.getAmount() + " for txn " + txn.getTransactionId());
        return true; // Wallet always succeeds if balance validated earlier
    }
    @Override
    public boolean refund(Transaction txn) {
        System.out.println("[Wallet] Crediting ₹" + txn.getAmount() + " back to wallet");
        return true;
    }
    @Override public PaymentMethod getMethod() { return PaymentMethod.WALLET; }
}

class NetBankingProcessor implements PaymentProcessor {
    @Override
    public boolean process(Transaction txn) {
        System.out.println("[Net Banking] Processing ₹" + txn.getAmount() + " for txn " + txn.getTransactionId());
        return Math.random() > 0.2;
    }
    @Override
    public boolean refund(Transaction txn) {
        System.out.println("[Net Banking] Initiating bank refund for txn " + txn.getTransactionId());
        return true;
    }
    @Override public PaymentMethod getMethod() { return PaymentMethod.NET_BANKING; }
}

// ─────────────────────────────────────────────
// 3. FACTORY PATTERN - Payment Processor Factory
// ─────────────────────────────────────────────

class PaymentProcessorFactory {
    private static final Map<PaymentMethod, PaymentProcessor> registry = new EnumMap<>(PaymentMethod.class);

    static {
        register(new UPIProcessor());
        register(new CreditCardProcessor());
        register(new WalletProcessor());
        register(new NetBankingProcessor());
    }

    public static void register(PaymentProcessor processor) {
        registry.put(processor.getMethod(), processor);
    }

    public static PaymentProcessor getProcessor(PaymentMethod method) {
        PaymentProcessor p = registry.get(method);
        if (p == null) throw new IllegalArgumentException("No processor for: " + method);
        return p;
    }
}

// ─────────────────────────────────────────────
// 4. DECORATOR PATTERN - Fee Calculation
// ─────────────────────────────────────────────

interface FeeCalculator {
    double calculate(double amount, PaymentMethod method);
}

class BaseFeeCalculator implements FeeCalculator {
    @Override
    public double calculate(double amount, PaymentMethod method) {
        return 0.0; // base has no fee
    }
}

abstract class FeeDecorator implements FeeCalculator {
    protected final FeeCalculator wrapped;
    FeeDecorator(FeeCalculator w) { this.wrapped = w; }
}

class PlatformFeeDecorator extends FeeDecorator {
    PlatformFeeDecorator(FeeCalculator w) { super(w); }
    @Override
    public double calculate(double amount, PaymentMethod method) {
        double base = wrapped.calculate(amount, method);
        double platformFee = switch (method) {
            case CREDIT_CARD  -> amount * 0.015; // 1.5%
            case DEBIT_CARD   -> amount * 0.008; // 0.8%
            case NET_BANKING  -> 5.0;            // flat ₹5
            default           -> 0.0;            // UPI, Wallet free
        };
        System.out.printf("[Fee] Platform fee: ₹%.2f%n", platformFee);
        return base + platformFee;
    }
}

class GSTFeeDecorator extends FeeDecorator {
    GSTFeeDecorator(FeeCalculator w) { super(w); }
    @Override
    public double calculate(double amount, PaymentMethod method) {
        double base = wrapped.calculate(amount, method);
        double gst  = base * 0.18; // 18% GST on platform fee
        System.out.printf("[Fee] GST: ₹%.2f%n", gst);
        return base + gst;
    }
}

// ─────────────────────────────────────────────
// 5. OBSERVER PATTERN - Transaction Events
// ─────────────────────────────────────────────

interface TransactionListener {
    void onTransactionEvent(Transaction txn, String eventType);
}

class SMSNotifier implements TransactionListener {
    @Override
    public void onTransactionEvent(Transaction txn, String eventType) {
        System.out.printf("[SMS] Sent to user %s: Txn %s %s for ₹%.2f%n",
            txn.getSenderId(), txn.getTransactionId(), eventType, txn.getAmount());
    }
}

class EmailNotifier implements TransactionListener {
    @Override
    public void onTransactionEvent(Transaction txn, String eventType) {
        System.out.printf("[Email] Sent: Txn %s %s for ₹%.2f%n",
            txn.getTransactionId(), eventType, txn.getAmount());
    }
}

class FraudDetector implements TransactionListener {
    private static final double FRAUD_THRESHOLD = 50_000.0;
    @Override
    public void onTransactionEvent(Transaction txn, String eventType) {
        if ("INITIATED".equals(eventType) && txn.getAmount() > FRAUD_THRESHOLD) {
            System.out.printf("[Fraud] ⚠ HIGH-VALUE TXN ALERT: ₹%.2f for txn %s%n",
                txn.getAmount(), txn.getTransactionId());
        }
    }
}

class AuditLogger implements TransactionListener {
    @Override
    public void onTransactionEvent(Transaction txn, String eventType) {
        System.out.printf("[Audit] Event=%s | %s%n", eventType, txn);
    }
}

class EventPublisher {
    private static EventPublisher instance;
    private final List<TransactionListener> listeners = new CopyOnWriteArrayList<>();

    private EventPublisher() {}

    public static synchronized EventPublisher getInstance() {
        if (instance == null) instance = new EventPublisher();
        return instance;
    }

    public void subscribe(TransactionListener l)   { listeners.add(l); }
    public void unsubscribe(TransactionListener l) { listeners.remove(l); }

    public void publish(Transaction txn, String event) {
        for (TransactionListener l : listeners) {
            l.onTransactionEvent(txn, event);
        }
    }
}

// ─────────────────────────────────────────────
// 6. STATE PATTERN - Transaction Lifecycle
// ─────────────────────────────────────────────

interface TransactionState {
    void process(TransactionContext ctx);
    void fail(TransactionContext ctx, String reason);
    void refund(TransactionContext ctx);
    String getStateName();
}

class TransactionContext {
    private TransactionState state;
    private final Transaction txn;

    TransactionContext(Transaction txn) {
        this.txn   = txn;
        this.state = new InitiatedState();
    }

    public void setState(TransactionState s) { this.state = s; }
    public TransactionState getState()       { return state; }
    public Transaction getTransaction()      { return txn; }

    public void process()               { state.process(this); }
    public void fail(String reason)     { state.fail(this, reason); }
    public void refund()                { state.refund(this); }
}

class InitiatedState implements TransactionState {
    @Override
    public void process(TransactionContext ctx) {
        System.out.println("[State] INITIATED → PROCESSING");
        ctx.getTransaction().setStatus(TransactionStatus.PROCESSING);
        ctx.setState(new ProcessingState());
    }
    @Override public void fail(TransactionContext ctx, String reason) {
        ctx.getTransaction().setStatus(TransactionStatus.FAILED);
        ctx.getTransaction().setFailureReason(reason);
        ctx.setState(new FailedState());
    }
    @Override public void refund(TransactionContext ctx) {
        System.out.println("[State] Cannot refund INITIATED txn");
    }
    @Override public String getStateName() { return "INITIATED"; }
}

class ProcessingState implements TransactionState {
    @Override
    public void process(TransactionContext ctx) {
        System.out.println("[State] PROCESSING → SUCCESS");
        ctx.getTransaction().setStatus(TransactionStatus.SUCCESS);
        ctx.setState(new SuccessState());
    }
    @Override public void fail(TransactionContext ctx, String reason) {
        ctx.getTransaction().setStatus(TransactionStatus.FAILED);
        ctx.getTransaction().setFailureReason(reason);
        ctx.setState(new FailedState());
    }
    @Override public void refund(TransactionContext ctx) {
        System.out.println("[State] Cannot refund PROCESSING txn");
    }
    @Override public String getStateName() { return "PROCESSING"; }
}

class SuccessState implements TransactionState {
    @Override public void process(TransactionContext ctx) { System.out.println("[State] Already SUCCESS"); }
    @Override public void fail(TransactionContext ctx, String reason) { System.out.println("[State] Cannot fail SUCCESS"); }
    @Override public void refund(TransactionContext ctx) {
        System.out.println("[State] SUCCESS → REFUNDED");
        ctx.getTransaction().setStatus(TransactionStatus.REFUNDED);
        ctx.setState(new RefundedState());
    }
    @Override public String getStateName() { return "SUCCESS"; }
}

class FailedState implements TransactionState {
    @Override public void process(TransactionContext ctx) { System.out.println("[State] Cannot process FAILED txn"); }
    @Override public void fail(TransactionContext ctx, String reason) { System.out.println("[State] Already FAILED"); }
    @Override public void refund(TransactionContext ctx)  { System.out.println("[State] Cannot refund FAILED txn"); }
    @Override public String getStateName() { return "FAILED"; }
}

class RefundedState implements TransactionState {
    @Override public void process(TransactionContext ctx) { System.out.println("[State] Cannot process REFUNDED"); }
    @Override public void fail(TransactionContext ctx, String reason) { System.out.println("[State] Cannot fail REFUNDED"); }
    @Override public void refund(TransactionContext ctx)  { System.out.println("[State] Already REFUNDED"); }
    @Override public String getStateName() { return "REFUNDED"; }
}

// ─────────────────────────────────────────────
// 7. COMMAND PATTERN - Payment & Refund
// ─────────────────────────────────────────────

interface PaymentCommand {
    boolean execute();
    boolean undo();
}

class MakePaymentCommand implements PaymentCommand {
    private final Transaction txn;
    private final PaymentProcessor processor;
    private final EventPublisher publisher;

    MakePaymentCommand(Transaction txn, PaymentProcessor processor) {
        this.txn       = txn;
        this.processor = processor;
        this.publisher = EventPublisher.getInstance();
    }

    @Override
    public boolean execute() {
        publisher.publish(txn, "INITIATED");
        TransactionContext ctx = new TransactionContext(txn);
        ctx.process(); // INITIATED → PROCESSING

        boolean success = processor.process(txn);
        if (success) {
            ctx.process(); // PROCESSING → SUCCESS
            publisher.publish(txn, "SUCCESS");
        } else {
            ctx.fail("Payment gateway failure");
            publisher.publish(txn, "FAILED");
        }
        return success;
    }

    @Override
    public boolean undo() {
        System.out.println("[Command] Undoing payment - initiating refund");
        return processor.refund(txn);
    }
}

class RefundCommand implements PaymentCommand {
    private final Transaction txn;
    private final PaymentProcessor processor;

    RefundCommand(Transaction txn, PaymentProcessor processor) {
        this.txn       = txn;
        this.processor = processor;
    }

    @Override
    public boolean execute() {
        System.out.println("[Refund Command] Processing refund for " + txn.getTransactionId());
        boolean success = processor.refund(txn);
        if (success) {
            txn.setStatus(TransactionStatus.REFUNDED);
            EventPublisher.getInstance().publish(txn, "REFUNDED");
        }
        return success;
    }

    @Override
    public boolean undo() {
        System.out.println("[Refund Command] Cannot undo a refund");
        return false;
    }
}

// Command invoker with history
class PaymentCommandInvoker {
    private final Deque<PaymentCommand> history = new ArrayDeque<>();

    public boolean invoke(PaymentCommand cmd) {
        boolean result = cmd.execute();
        if (result) history.push(cmd);
        return result;
    }

    public boolean undoLast() {
        if (history.isEmpty()) { System.out.println("No commands to undo"); return false; }
        return history.pop().undo();
    }
}

// ─────────────────────────────────────────────
// 8. REPOSITORY PATTERN - Data Access
// ─────────────────────────────────────────────

interface UserRepository {
    void save(User user);
    Optional<User> findById(String userId);
    List<User> findAll();
}

interface TransactionRepository {
    void save(Transaction txn);
    Optional<Transaction> findById(String txnId);
    List<Transaction> findByUserId(String userId);
    List<Transaction> findAll();
}

class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> store = new ConcurrentHashMap<>();

    @Override public void save(User user)                 { store.put(user.getUserId(), user); }
    @Override public Optional<User> findById(String id)   { return Optional.ofNullable(store.get(id)); }
    @Override public List<User> findAll()                 { return new ArrayList<>(store.values()); }
}

class InMemoryTransactionRepository implements TransactionRepository {
    private final Map<String, Transaction> store = new ConcurrentHashMap<>();

    @Override public void save(Transaction txn)                { store.put(txn.getTransactionId(), txn); }
    @Override public Optional<Transaction> findById(String id) { return Optional.ofNullable(store.get(id)); }
    @Override public List<Transaction> findAll()               { return new ArrayList<>(store.values()); }
    @Override public List<Transaction> findByUserId(String userId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : store.values()) {
            if (t.getSenderId().equals(userId) || t.getReceiverId().equals(userId)) result.add(t);
        }
        return result;
    }
}

// ─────────────────────────────────────────────
// 9. SINGLETON - Core Payment Service
// ─────────────────────────────────────────────

class PaymentService {
    private static volatile PaymentService instance;
    private final TransactionRepository txnRepo;
    private final UserRepository userRepo;
    private final FeeCalculator feeCalculator;
    private final PaymentCommandInvoker invoker;

    private PaymentService() {
        this.txnRepo        = new InMemoryTransactionRepository();
        this.userRepo       = new InMemoryUserRepository();
        this.feeCalculator  = new GSTFeeDecorator(new PlatformFeeDecorator(new BaseFeeCalculator()));
        this.invoker        = new PaymentCommandInvoker();

        // Register observers
        EventPublisher pub = EventPublisher.getInstance();
        pub.subscribe(new SMSNotifier());
        pub.subscribe(new EmailNotifier());
        pub.subscribe(new FraudDetector());
        pub.subscribe(new AuditLogger());
    }

    // Double-checked locking Singleton
    public static PaymentService getInstance() {
        if (instance == null) {
            synchronized (PaymentService.class) {
                if (instance == null) instance = new PaymentService();
            }
        }
        return instance;
    }

    public User registerUser(User user) {
        userRepo.save(user);
        System.out.println("[PaymentService] Registered: " + user);
        return user;
    }

    public Optional<User> getUser(String userId) { return userRepo.findById(userId); }

    /**
     * Core payment flow
     */
    public Transaction pay(String senderId, String receiverId, double amount, PaymentMethod method) {
        // Validate users
        User sender = userRepo.findById(senderId)
            .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + senderId));

        // Validate wallet balance for wallet payments
        if (method == PaymentMethod.WALLET && sender.getWalletBalance() < amount) {
            throw new IllegalStateException("Insufficient wallet balance");
        }

        // Calculate fees (Decorator pattern)
        double fees = feeCalculator.calculate(amount, method);

        // Build Transaction (Builder pattern)
        Transaction txn = new Transaction.Builder()
            .senderId(senderId)
            .receiverId(receiverId)
            .amount(amount)
            .fees(fees)
            .method(method)
            .build();

        // Get Processor (Factory + Strategy)
        PaymentProcessor processor = PaymentProcessorFactory.getProcessor(method);

        // Execute via Command (Command pattern)
        MakePaymentCommand cmd = new MakePaymentCommand(txn, processor);
        invoker.invoke(cmd);

        // Persist
        txnRepo.save(txn);
        return txn;
    }

    public boolean refund(String txnId) {
        Transaction txn = txnRepo.findById(txnId)
            .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + txnId));

        if (txn.getStatus() != TransactionStatus.SUCCESS) {
            throw new IllegalStateException("Can only refund SUCCESS transactions");
        }

        PaymentProcessor processor = PaymentProcessorFactory.getProcessor(txn.getMethod());
        RefundCommand cmd = new RefundCommand(txn, processor);
        boolean result = invoker.invoke(cmd);
        if (result) txnRepo.save(txn);
        return result;
    }

    public List<Transaction> getTransactionHistory(String userId) {
        return txnRepo.findByUserId(userId);
    }

    public List<Transaction> getAllTransactions() {
        return txnRepo.findAll();
    }
}

// ─────────────────────────────────────────────
// 10. FACADE PATTERN - Simplified Client API
// ─────────────────────────────────────────────

class PaymentFacade {
    private final PaymentService service = PaymentService.getInstance();

    public void registerUser(String id, String name, String phone, String email, double walletBalance) {
        User user = new User.Builder()
            .userId(id).name(name).phone(phone).email(email).walletBalance(walletBalance)
            .build();
        service.registerUser(user);
    }

    public Transaction sendMoney(String from, String to, double amount, PaymentMethod method) {
        System.out.println("\n══════════════════════════════════════════");
        System.out.printf(" Initiating ₹%.2f payment via %s%n", amount, method);
        System.out.println("══════════════════════════════════════════");
        Transaction txn = service.pay(from, to, amount, method);
        System.out.println(" Result: " + txn.getStatus());
        System.out.println("══════════════════════════════════════════\n");
        return txn;
    }

    public void refundTransaction(String txnId) {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println(" Processing Refund for TXN: " + txnId);
        boolean result = service.refund(txnId);
        System.out.println(" Refund " + (result ? "SUCCESS" : "FAILED"));
        System.out.println("══════════════════════════════════════════\n");
    }

    public void printHistory(String userId) {
        System.out.println("\n--- Transaction History for " + userId + " ---");
        List<Transaction> txns = service.getTransactionHistory(userId);
        if (txns.isEmpty()) System.out.println("No transactions found.");
        else txns.forEach(System.out::println);
        System.out.println();
    }
}

// ─────────────────────────────────────────────
// MAIN - Demo / Test
// ─────────────────────────────────────────────

public class PaymentAppLLD {
    public static void main(String[] args) {
        PaymentFacade facade = new PaymentFacade();

        // Register users
        facade.registerUser("U001", "Alice",   "9876543210", "alice@email.com",  5000.0);
        facade.registerUser("U002", "Bob",     "9123456789", "bob@email.com",    2000.0);
        facade.registerUser("U003", "Charlie", "9000000000", "charlie@email.com", 100.0);

        // Payment via UPI
        Transaction t1 = facade.sendMoney("U001", "U002", 500.0,  PaymentMethod.UPI);

        // Payment via Credit Card (has fees)
        Transaction t2 = facade.sendMoney("U001", "U003", 2000.0, PaymentMethod.CREDIT_CARD);

        // Payment via Wallet
        Transaction t3 = facade.sendMoney("U002", "U001", 300.0,  PaymentMethod.WALLET);

        // High-value transaction (triggers fraud alert)
        Transaction t4 = facade.sendMoney("U001", "U002", 75000.0, PaymentMethod.NET_BANKING);

        // Attempt refund on a successful transaction
        if (t1.getStatus() == TransactionStatus.SUCCESS) {
            facade.refundTransaction(t1.getTransactionId());
        }

        // Print history
        facade.printHistory("U001");
        facade.printHistory("U002");

        // Demonstrate error handling
        System.out.println("=== Testing insufficient wallet balance ===");
        try {
            facade.sendMoney("U003", "U001", 5000.0, PaymentMethod.WALLET);
        } catch (IllegalStateException e) {
            System.out.println("[Error Caught] " + e.getMessage());
        }
    }
}
