<template>
    <div class="container" v-if="visibleAccountInfo">
        <div class="table-accounts">
            <table>
                <tr>
                    <th>Номер счета</th>
                    <th>Тип счета</th>
                    <th>Контрольный ключ</th>
                    <th>Дата открытия счета</th>
                    <th>Дата исключения информации о счете</th>
                    <th>Статус счета</th>
                    <th>Actions</th>
                </tr>
                <tr v-for="item in Accounts" :key="item.account">
                    <td>{{ item.account }}</td>
                    <td>{{ item.regulationAccountType.code }}</td>
                    <td>{{ item.controlKey }}</td>
                    <td>{{ item.dateIn }}</td>
                    <td>{{ item.dateOut }}</td>
                    <td>{{ item.account }}</td>
                    <td>{{ item.accountStatus.code }}</td>
                    <td>
                        <button @click="openPopover(item)">
                            <img src="/more_horiz.png" />
                        </button>

                        <popover v-if="selectedItem && selectedItem.bic === item.bic" @close="closePopover">
                            <div class="popover">
                                <button @click="closePopover()">Отменить</button>
                                <button @click="openSaveModal()">Обновить</button>
                                <button @click="deleteItem()">Удалить</button>
                            </div>

                        </popover>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</template>
<script>
props: ['visibleAccountInfo', 'Accounts']
</script>