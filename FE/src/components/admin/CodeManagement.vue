<template>
  <div class="min-h-screen bg-gray-50 py-8 px-4 sm:px-6 lg:px-8">
    <div class="max-w-7xl mx-auto">
      <div class="mb-8">
        <h1 class="text-2xl font-bold text-gray-900">코드 관리</h1>
        <p class="mt-2 text-sm text-gray-600">시스템에서 사용되는 코드를 관리합니다.</p>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div class="bg-white rounded-lg shadow">
          <div class="p-4 border-b border-gray-200 flex justify-between items-center">
            <h2 class="text-lg font-medium text-gray-900">코드 그룹</h2>
            <div class="flex space-x-2">
              <button @click="openAddGroupModal" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700">
                <PlusIcon class="h-4 w-4 mr-1" />
                추가
              </button>
              <button @click="deleteSelectedGroup" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700">
                <TrashIcon class="h-4 w-4 mr-1" />
                삭제
              </button>
            </div>
          </div>
          <div class="p-4">
            <table class="min-w-full divide-y divide-gray-200">
              <thead class="bg-gray-50">
                <tr>
                  <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">순번</th>
                  <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">그룹 ID</th>
                  <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">그룹명</th>
                </tr>
              </thead>
              <tbody class="bg-white divide-y divide-gray-200">
                <tr v-for="(group, index) in codeGroups" 
                    :key="group.groupId" 
                    @click="selectGroup(group)"
                    class="hover:bg-gray-50 cursor-pointer"
                    :class="{'bg-indigo-50': selectedGroup?.groupId === group.groupId}">
                  <td class="px-4 py-3 whitespace-nowrap text-sm text-gray-500">{{ index + 1 }}</td>
                  <td class="px-4 py-3 whitespace-nowrap text-sm text-gray-900">{{ group.groupId }}</td>
                  <td class="px-4 py-3 whitespace-nowrap text-sm font-medium text-gray-900">{{ group.groupName }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <div class="lg:col-span-2 bg-white rounded-lg shadow">
          <div class="p-4 border-b border-gray-200 flex justify-between items-center">
            <h2 class="text-lg font-medium text-gray-900">코드 상세</h2>
            <div class="flex space-x-2">
              <button @click="openAddCodeModal" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700">
                <PlusIcon class="h-4 w-4 mr-1" />
                추가
              </button>
              <button @click="deleteSelectedCode" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700">
                <TrashIcon class="h-4 w-4 mr-1" />
                삭제
              </button>
            </div>
          </div>
          <div class="p-4">
            <table class="min-w-full divide-y divide-gray-200">
              <thead class="bg-gray-50">
                <tr>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">순서</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">코드명</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">코드ID</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">코드설명</th>
                </tr>
              </thead>
              <tbody class="bg-white divide-y divide-gray-200">
                <tr v-for="code in codes" :key="code.codeId" 
                    @click="selectCode(code)"
                    class="hover:bg-gray-50 cursor-pointer"
                    :class="{'bg-indigo-50': selectedCode?.codeId === code.codeId}">
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ code.order }}</td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{{ code.codeName }}</td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ code.codeId }}</td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ code.description }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div v-if="showGroupModal" class="fixed inset-0 bg-gray-500 bg-opacity-75 flex items-center justify-center">
    <div class="bg-white rounded-lg p-6 w-full max-w-md">
      <h3 class="text-lg font-medium text-gray-900 mb-4">코드 그룹 추가</h3>
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium text-gray-700">그룹 ID</label>
          <input v-model="newGroup.groupId" 
                 type="text" 
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500"
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">그룹명</label>
          <input v-model="newGroup.groupName" 
                 type="text" 
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500"
        </div>
        <div class="flex justify-end space-x-3">
          <button @click="closeGroupModal" class="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200">
            취소
          </button>
          <button @click="addGroup" class="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-md hover:bg-indigo-700">
            추가
          </button>
        </div>
      </div>
    </div>
  </div>

  <div v-if="showCodeModal" class="fixed inset-0 bg-gray-500 bg-opacity-75 flex items-center justify-center">
    <div class="bg-white rounded-lg p-6 w-full max-w-md">
      <h3 class="text-lg font-medium text-gray-900 mb-4">코드 추가</h3>
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium text-gray-700">코드 ID</label>
          <input v-model="newCode.codeId" type="text" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">코드명</label>
          <input v-model="newCode.codeName" type="text" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">설명</label>
          <input v-model="newCode.description" type="text" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">순서</label>
          <input v-model="newCode.order" type="number" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div class="flex justify-end space-x-3">
          <button @click="closeCodeModal" class="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200">
            취소
          </button>
          <button @click="addCode" class="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-md hover:bg-indigo-700">
            추가
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { PlusIcon, TrashIcon } from '@heroicons/vue/24/outline'

const codeGroups = ref([
  { groupId: 'anmctg', groupName: '동물구분', codes: [
    { order: 1, codeName: '강아지', codeId: 'dog', description: '강아지 구분' },
    { order: 2, codeName: '고양이', codeId: 'cat', description: '고양이 구분' }
  ]},
  { groupId: 'dogctg', groupName: '개품종', codes: [
    { order: 1, codeName: '말티즈', codeId: 'maltese', description: '말티즈 품종' },
    { order: 2, codeName: '푸들', codeId: 'poodle', description: '푸들 품종' }
  ]},
  { groupId: 'catctg', groupName: '고양이품종', codes: [
    { order: 1, codeName: '페르시안', codeId: 'persian', description: '페르시안 품종' },
    { order: 2, codeName: '러시안블루', codeId: 'russian-blue', description: '러시안블루 품종' }
  ]}
])

const codes = ref([])

const selectedGroup = ref(null)
const selectedCode = ref(null)

const selectGroup = (group) => {
  selectedGroup.value = group
  codes.value = group.codes || []
}

const selectCode = (code) => {
  selectedCode.value = code
}

const showGroupModal = ref(false)
const showCodeModal = ref(false)

const newGroup = ref({
  groupId: '',
  groupName: ''
})

const newCode = ref({
  codeId: '',
  codeName: '',
  description: '',
  order: 1
})

const openAddGroupModal = () => {
  showGroupModal.value = true
  newGroup.value = { groupId: '', groupName: '' }
}

const closeGroupModal = () => {
  showGroupModal.value = false
}

const addGroup = () => {
  if (newGroup.value.groupId && newGroup.value.groupName) {
    const isDuplicate = codeGroups.value.some(group => group.groupId === newGroup.value.groupId)
    if (isDuplicate) {
      alert('이미 존재하는 그룹 ID입니다.')
      return
    }

    codeGroups.value.push({
      ...newGroup.value,
      codes: []
    })
    closeGroupModal()
  }
}

const openAddCodeModal = () => {
  if (!selectedGroup.value) {
    alert('코드 그룹을 먼저 선택해주세요.')
    return
  }
  showCodeModal.value = true
  newCode.value = {
    codeId: '',
    codeName: '',
    description: '',
    order: codes.value.length + 1
  }
}

const closeCodeModal = () => {
  showCodeModal.value = false
}

const addCode = () => {
  if (!selectedGroup.value) return
  
  if (newCode.value.codeId && newCode.value.codeName) {
    const groupIndex = codeGroups.value.findIndex(g => g.groupId === selectedGroup.value.groupId)
    if (groupIndex !== -1) {
      if (!codeGroups.value[groupIndex].codes) {
        codeGroups.value[groupIndex].codes = []
      }
      codeGroups.value[groupIndex].codes.push({ ...newCode.value })
      codes.value = codeGroups.value[groupIndex].codes
    }
    closeCodeModal()
  }
}

const deleteSelectedGroup = () => {
  // TODO: 선택된 그룹 삭제 구현
}

const deleteSelectedCode = () => {
  // TODO: 선택된 코드 삭제 구현
}
</script>