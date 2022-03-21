import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Buttons {
    AddGame("Добавить игру")
    ,DeleteGame("Удалить игру")
    ,RefreshGameList("Очистить список");
    ,StartVote("Начать голосование")
    
    private final String id;

}
