Feature: FizzBuzz動作確認

    Scenario Outline: 入力として３と５の倍数を入力してボタンをクリックするとFizzBuzzという文字列が表示される
        Given FizzBuzzアプリが起動されている
        When 入力フィールドに"<inputStr>"を入力する
        And ボタンをクリックする
        Then ボタンの下に"FizzBuzz"という文字列が表示される
        Examples:
            | inputStr |
            | 15       |