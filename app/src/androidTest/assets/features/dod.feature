Feature:

  Scenario: DoD登録すると表示される
    Given DoDリストページを開く
    And DoDフォームに"Long Method"を入力
    When DoD登録フォームをサブミット
    And DoDリストの"Long Method"をタップ
    Then DoD詳細に"Long Method"が表示される

